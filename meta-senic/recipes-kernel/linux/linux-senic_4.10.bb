SECTION = "kernel"
DESCRIPTION = "Mainline Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

COMPATIBLE_MACHINE = "(senic-hub-beta|senic-hub)"

inherit kernel

require recipes-kernel/linux/linux-dtb.inc

KBRANCH = "senic/4.10"
SRCREV = "43be15e4e16d6b2162a5b13e8633e23720ee9f5b"

PV = "4.10+git${SRCPV}"
S = "${WORKDIR}/git"

SRC_URI = "git://github.com/getsenic/senic-os-linux.git;protocol=git;branch=${KBRANCH} \
        file://defconfig \
        "