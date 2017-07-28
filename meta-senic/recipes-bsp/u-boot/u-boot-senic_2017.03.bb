DESCRIPTION="Upstream's U-boot configured for sunxi devices"
LICENSE = "GPLv2"

require recipes-bsp/u-boot/u-boot.inc

#Mender integration
require recipes-bsp/u-boot/u-boot-mender.inc
PROVIDES += "u-boot"
RPROVIDES_${PN} += "u-boot"
BOOTENV_SIZE = "0x20000"

DEPENDS += "dtc-native"

COMPATIBLE_MACHINE = "(senic-hub-beta|senic-hub)"

SRCBRANCH = "senic/v2017.03"
SRC_URI = "git://git@github.com/getsenic/senic-os-uboot.git;protocol=ssh;branch=${SRCBRANCH}; \
	   file://boot.cmd \
	   file://0001-sun8i-configs-Add-CONFIG_BOOTCOUNT_LIMIT-ENV-for-men.patch \
	   file://0002-sunxi-common-Remove-variables-used-by-Mender.patch \
	   "

SRCREV = "5233f173335a61b1e2b9120c55422a8d60ff7ffe"
PV = "v2017.03+git${SRCPV}"
S = "${WORKDIR}/git"

SPL_BINARY="u-boot-sunxi-with-spl.bin"

UBOOT_ENV_SUFFIX = "scr"
UBOOT_ENV = "boot"

do_compile_append() {
    ${B}/tools/mkimage -C none -A arm -T script -d ${WORKDIR}/boot.cmd ${WORKDIR}/${UBOOT_ENV_BINARY}
}