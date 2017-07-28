SUMMARY = "U-Boot bootloader fw_printenv/setenv utilities"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"
SECTION = "bootloader"
DEPENDS = "mtd-utils"

require recipes-bsp/u-boot/u-boot-fw-utils-mender.inc

SRCBRANCH = "senic/v2017.03"
SRC_URI = "git://git@github.com/getsenic/senic-os-uboot.git;protocol=ssh;branch=${SRCBRANCH}; \
	   file://boot.cmd \
	   file://0001-sun8i-configs-Add-CONFIG_BOOTCOUNT_LIMIT-ENV-for-men.patch \
	   file://0002-sunxi-common-Remove-variables-used-by-Mender.patch \
	   "

SRCREV = "5233f173335a61b1e2b9120c55422a8d60ff7ffe"
PV = "v2017.03+git${SRCPV}"
S = "${WORKDIR}/git"

# For Mender.io integration
BOOTENV_SIZE = "0x20000"
PROVIDES += "u-boot-fw-utils"
RPROVIDES_${PN} += "u-boot-fw-utils"

INSANE_SKIP_${PN} = "already-stripped"
EXTRA_OEMAKE_class-target = 'CROSS_COMPILE=${TARGET_PREFIX} CC="${CC} ${CFLAGS} ${LDFLAGS}" V=1'
EXTRA_OEMAKE_class-cross = 'ARCH=${TARGET_ARCH} CC="${CC} ${CFLAGS} ${LDFLAGS}" V=1'

inherit uboot-config

do_compile () {
	oe_runmake ${UBOOT_MACHINE}
	oe_runmake env
}

do_install () {
	install -d ${D}${base_sbindir}
	install -d ${D}${sysconfdir}
	install -m 755 ${S}/tools/env/fw_printenv ${D}${base_sbindir}/fw_printenv
	install -m 755 ${S}/tools/env/fw_printenv ${D}${base_sbindir}/fw_setenv
	install -m 0644 ${S}/tools/env/fw_env.config ${D}${sysconfdir}/fw_env.config
}

do_install_class-cross () {
	install -d ${D}${bindir_cross}
	install -m 755 ${S}/tools/env/fw_printenv ${D}${bindir_cross}/fw_printenv
	install -m 755 ${S}/tools/env/fw_printenv ${D}${bindir_cross}/fw_setenv
}

SYSROOT_DIRS_append_class-cross = " ${bindir_cross}"

PACKAGE_ARCH = "${MACHINE_ARCH}"
BBCLASSEXTEND = "cross"
