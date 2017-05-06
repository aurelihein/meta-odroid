FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.11"

SRCREV ?= "a351e9b9fc24e982ec2f0e76379a49826036da12"
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;branch=master"

O_KERNEL_CONFIG_odroid-xu3  = "odroid-xu3"
O_KERNEL_CONFIG_odroid-xu4  = "odroid-xu3"
O_KERNEL_CONFIG_odroid-xu3-lite  = "odroid-xu3"
O_KERNEL_CONFIG_odroid-c2  = "odroid-c2"

require linux-stable.inc

DEPENDS += "u-boot-mkimage-native"

do_compile_append_odroid-c2 () {
	uboot-mkimage -A arm64 -O linux -T kernel -C none -a 0x1080000 -e 0x1080000 -n master -d ${B}/arch/${ARCH}/boot/Image ${KERNEL_OUTPUT_DIR}/uImage
}

do_install_append_odroid-c2 () {
	install -m 0644 ${KERNEL_OUTPUT_DIR}/uImage ${D}/${KERNEL_IMAGEDEST}/uImage
}

do_deploy_append_odroid-c2 () {
	 install -m 0644 ${D}/${KERNEL_IMAGEDEST}/uImage ${DEPLOY_DIR_IMAGE}/uImage
} 

FILES_${PN} += "/boot/uImage"

COMPATIBLE_MACHINE = "(odroid-c2|odroid-xu3|odroid-xu4|odroid-xu3-lite)"