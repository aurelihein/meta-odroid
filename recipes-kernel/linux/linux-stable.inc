DESCRIPTION = "Linux Kernel"
SECTION = "kernel"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

KBASE_VERSION = "${@d.getVar('LINUX_VERSION').split('.')[0]}.${@d.getVar('LINUX_VERSION').split('.')[1]}"
KBRANCH ?= "linux-${KBASE_VERSION}.y"
MBRANCH ?= "yocto-${KBASE_VERSION}"

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

PV = "${LINUX_VERSION}+git${SRCPV}"

KCONF_BSP_AUDIT_LEVEL = "2"
DEPENDS += "openssl-native util-linux-native"

KMETA = "kernel-meta"
KMACHINE_odroid-hc1 = "odroid-hc1"
KMACHINE_odroid-xu3 = "odroid-xu3"
KMACHINE_odroid-xu3-lite = "odroid-xu3-lite"
KMACHINE_odroid-xu4 = "odroid-xu4"
KMACHINE_odroid-c2 = "odroid-c2"
KMACHINE_odroid-c1 = "odroid-c1"

KMETA_SRC_URI ?= " git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=${MBRANCH};destsuffix=${KMETA}"
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;branch=${KBRANCH}"
SRC_URI_append = "${KMETA_SRC_URI}" 
SRC_URI_append = " file://odroid-kmeta;type=kmeta;name=odroid-kmeta;destsuffix=odroid-kmeta"
SRC_URI_append = " file://odroid/odroid.scc"
KERNEL_EXTRA_FEATURES_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'apparmor', 'features/security/apparmor.scc', '', d)}"

COMPATIBLE_MACHINE = "(odroid-c1|odroid-c2|odroid-xu3|odroid-xu4|odroid-xu3-lite|odroid-hc1|odroid-h2)"
