# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# Unable to find any files that looked like license statements. Check the accompanying
# documentation and source headers and set LICENSE and LIC_FILES_CHKSUM accordingly.
#
# NOTE: LICENSE is being set to "CLOSED" to allow you to at least start building - if
# this is not accurate with respect to the licensing of the software being built (it
# will not be in most cases) you must specify the correct value before using this
# recipe for anything other than initial testing/development!
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "file://authorized_keys"
SRC_URI += "file://BleOnboarding"

do_configure () {
	# Specify any needed configure commands here
	:
}

do_compile () {
	# Specify compilation commands here
	:
}

do_install () {
    install -d ${D}/${ROOT_HOME}/.ssh -m 0700
    install -m 0600 ${WORKDIR}/authorized_keys ${D}/${ROOT_HOME}/.ssh/
    # install the debug network connection for the senic office
    install -d ${D}/${sysconfdir}/NetworkManager/system-connections -m 0700
    install -m 0600 ${WORKDIR}/BleOnboarding ${D}/${sysconfdir}/NetworkManager/system-connections/BleOnboarding
}

FILES_${PN} = "\
    ${ROOT_HOME} \
    ${sysconfdir}/NetworkManager/system-connections/BleOnboarding \
"

