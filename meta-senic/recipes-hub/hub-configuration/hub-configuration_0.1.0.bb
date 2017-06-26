SUMMARY = "Configure the senic hub"
DESCRIPTION = "\
Creates various configuration files"
DEPENDS_${PN} = "\
  python-supervisor \
  bluez5 \
"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI = "file://supervisor_senic_hub.conf"
SRC_URI = "file://supervisor_bluenet.conf"
SRC_URI += "file://production.ini"
SRC_URI += "file://locales.sh"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE.txt;md5=62d64e0a0688cba2e9ede69d1f702e1c"

inherit senic-base
inherit logging
inherit useradd

do_configure[deptask] = "do_install"

python do_compile() {
  render_template('supervisor_senic_hub.conf')
  render_template('supervisor_bluenet.conf')
  render_template('production.ini')
  render_template('locales.sh')
}

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "-U -d ${SNC_BACKEND_DEPLOY_LOCATION} ${SNC_RUNTIME_USER}"


do_install() {
    # create and populate the deployment location
    install -m 0755 -o ${SNC_BUILD_USER} -g ${SNC_RUNTIME_USER} -d ${D}${SNC_BACKEND_DEPLOY_LOCATION}
    install -m 0755 -o ${SNC_RUNTIME_USER} -g ${SNC_RUNTIME_USER} -d ${D}${SNC_BACKEND_DATA_LOCATION}
    install -m 0755 -o ${SNC_RUNTIME_USER} -g ${SNC_RUNTIME_USER} -d ${D}${SNC_BACKEND_DATA_LOCATION}/logs
    install -m 0755 -o ${SNC_BUILD_USER} -g ${SNC_RUNTIME_USER} ${WORKDIR}/production.ini ${D}${SNC_BACKEND_DEPLOY_LOCATION}/production.ini
    install -m 0755 -d ${D}${SNC_HASS_DATA_LOCATION}

    # configure supervisor processes
    install -m 0755 -d ${D}${sysconfdir}/supervisor/conf.d/
    install -m 0755 ${WORKDIR}/supervisor_senic_hub.conf ${D}${sysconfdir}/supervisor/conf.d/senic_hub.conf
    install -m 0755 ${WORKDIR}/supervisor_bluenet.conf ${D}${sysconfdir}/supervisor/conf.d/bluenet.conf

    # global system configuration
    install -m 0755 -d ${D}${sysconfdir}/profile.d/
    install -m 644 ${WORKDIR}/locales.sh ${D}${sysconfdir}/profile.d/locales.sh

}

FILES_${PN} = "\
    ${SNC_BACKEND_DATA_LOCATION}/logs \
    ${SNC_BACKEND_DEPLOY_LOCATION} \
    ${SNC_HASS_DATA_LOCATION} \
    ${sysconfdir}/supervisor/conf.d/senic_hub.conf \
    ${sysconfdir}/supervisor/conf.d/bluenet.conf \
    ${sysconfdir}/profile.d/locales.sh \
"
