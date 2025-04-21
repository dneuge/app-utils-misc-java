package de.energiequant.apputils.misc;

import java.util.Collection;
import java.util.Optional;

import de.energiequant.apputils.misc.attribution.CopyrightNoticeProvider;
import de.energiequant.apputils.misc.attribution.License;
import de.energiequant.apputils.misc.attribution.Project;

public interface ApplicationInfo {

    Collection<Project> getDependencies();

    CopyrightNoticeProvider getCopyrightNoticeProvider();

    String getApplicationName();

    String getApplicationUrl();

    String getApplicationVersion();

    String getApplicationCopyright();

    License getEffectiveLicense();

    Optional<String> getDisclaimer();

    Optional<String> getDisclaimerAcceptanceText();
}
