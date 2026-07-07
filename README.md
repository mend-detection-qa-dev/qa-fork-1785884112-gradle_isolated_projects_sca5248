# gradle-isolated-projects-repro

Minimal Gradle project for verifying **SCA-5248** end-to-end via Mend Repo Integration.

## Why this repo exists

SCA-5248 was originally reproduced against `mend-detection-qa-dev/gradle` (Gradle's own
source). That repo triggers the bug, but it can **never** show detected Gradle
dependencies — building it needs internal, unpublished subprojects (`:core-platform`,
`:distributions-basics`), so any scanner returns 0 Gradle deps regardless of the fix.
That makes it useless for confirming the fix actually resolves deps.

This repo is the clean control:

- **Gradle 8.14.3** (wrapper pinned) — a version that enforces the conflict.
- `gradle.properties` sets `org.gradle.unsafe.isolated-projects=true` — the exact
  trigger from the ticket.
- Only **public** external dependencies with a real transitive tree, so a green scan
  is unambiguous.

## Declared dependencies (ground truth)

Direct:
- `com.google.guava:guava:33.4.6-jre`
- `org.apache.commons:commons-lang3:3.20.0`
- `junit:junit:4.13.2` (test)

Guava pulls transitives: `failureaccess`, `listenablefuture`, `jsr305`,
`checker-qual`, `error_prone_annotations`.

## Expected results

| Mend version | Result |
|--------------|--------|
| **Unpatched** (before SCA-5248) | `BUILD FAILED` — *"The configuration cache cannot be disabled when isolated projects is enabled."* 0 deps resolved. |
| **Patched** (SCA-5248 on `dev`) | Build succeeds; update request contains the guava tree + commons-lang3 + junit, all `dependencyType: GRADLE`. |

If the patched scan shows the GRADLE deps above, the fix is verified end-to-end.
