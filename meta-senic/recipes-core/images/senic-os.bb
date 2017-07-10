LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit core-image

IMAGE_BOOT_FILES = "zImage zImage-${KERNEL_DEVICETREE} boot.scr"

IMAGE_INSTALL = " \
  ${@bb.utils.contains("DISTRO_FEATURES", "bluetooth", "bluez5-noinst-tools", "", d)} \
  ${@bb.utils.contains("DISTRO_FEATURES", "wifi", "linux-firmware-mediatek", "",d)} \	
  packagegroup-core-boot \
  packagegroup-base \	
  packagegroup-core-ssh-openssh \
  ifupdown \
  openssl \
  lighttpd \
  os-release \
  glibc-locale \
  glibc-utils \ 
  networkmanager \
  ntp \
  python \
  python-supervisor \
  python3 \
  python3-asn1crypto \
  python3-cffi \
  python3-chardet \
  python3-click \
  python3-colander \
  python3-contextlib2 \
  python3-cornice \
  python3-cryptography \
  python3-cryptoyaml \
  python3-dbus \
  python3-enum-compat \
  python3-gatt \
  python3-hupper \
  python3-idna \
  python3-iso8601 \
  python3-lightify \
  python3-multiprocessing \
  python3-netdisco \
  python3-netifaces \
  python3-python-networkmanager \
  python3-nuimo \
  python3-pastedeploy \
  python3-phue \
  python3-pip \
  python3-plaster \
  python3-pygobject \
  python3-pydoc \
  python3-pyramid \
  python3-pyramid-tm \
  python3-pytz \
  python3-pyyaml \
  python3-raven \
  python3-repoze-lru \
  python3-requests \
  python3-senic-hub \
  python3-simplejson \
  python3-six \
  python3-soco \
  python3-transaction \
  python3-translationstring \
  python3-urllib3 \
  python3-venusian \
  python3-waitress \
  python3-webob \
  python3-websocket-client \
  python3-xmltodict \
  python3-zeroconf \
  python3-zope.deprecation \
  python3-zope.interface \
  hub-configuration \
  hub-configuration \
  hub-installation \
"

# Custom kernel module to handle Button interrupts
# IMAGE_INSTALL_append = " senic-button"

