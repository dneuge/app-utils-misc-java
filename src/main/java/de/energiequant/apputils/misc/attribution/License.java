package de.energiequant.apputils.misc.attribution;

import static java.util.Arrays.asList;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.energiequant.apputils.misc.ResourceUtils;

public enum License {
    APACHE2(
        "Apache License, Version 2.0",
        "apache2.html",
        asList(
            "Apache 2.0",
            "Apache-2.0",
            "Apache License, Version 2.0",
            "The Apache Software License, Version 2.0"
        )
    ),

    CC_BY_SA_4_0(
        "Creative Commons Attribution Share Alike 4.0 International",
        "cc-by-sa-4_0.html",
        asList(
            "CC-BY-SA-4.0"
        )
    ),

    MIT(
        "MIT License",
        "mit.html",
        asList(
            "MIT",
            "MIT License"
        )
    );

    private static class LicenseViolation extends RuntimeException {
        public LicenseViolation(String msg) {
            super(msg);
        }
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(License.class);

    private final String canonicalName;
    private final String text;
    private final Collection<String> aliases;

    private static final Map<String, License> LICENSES_BY_ALIAS = new HashMap<>();

    static {
        for (License license : values()) {
            for (String alias : license.aliases) {
                License previous = LICENSES_BY_ALIAS.put(alias, license);
                if (previous != null) {
                    String msg = "Duplicate alias for licenses " + previous + " and " + license + ": \"" + alias + "\"";
                    LOGGER.error(msg);
                    throw new LicenseViolation(msg);
                }
            }
        }
    }

    private License(String canonicalName, String filePath, Collection<String> aliases) {
        this.canonicalName = canonicalName;
        this.aliases = aliases;
        this.text = loadLicenseText(filePath);
    }

    public String getCanonicalName() {
        return canonicalName;
    }

    public String getText() {
        return text;
    }

    public static License byAlias(String alias) {
        License license = LICENSES_BY_ALIAS.get(alias);
        if (license == null) {
            throw new LicenseViolation("Unknown license alias: \"" + alias + "\"");
        }
        return license;
    }

    private String loadLicenseText(String filePath) {
        return ResourceUtils.getRelativeResourceContentAsString(getClass(), filePath, StandardCharsets.UTF_8)
                            .orElseThrow(() -> new LicenseViolation(
                                "missing license text for " + this + ": resource " + filePath + " not found"
                            ));
    }
}
