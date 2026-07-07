package com.mend.repro;

import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;

/** Trivial class that references the external deps so they're real compile-classpath entries. */
public final class App {
    public static void main(String[] args) {
        String joined = Joiner.on(", ").join("isolated", "projects", "repro");
        System.out.println(StringUtils.capitalize(joined));
    }
}
