FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = " file://server.crt"

MENDER_SERVER_URL = "https://mender.senic.com"
