FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.9.177"
KBRANCH ?= "odroidn2-4.9.y"
SRCREV ?= "c1116eecfb0a085f813ad8925b083e7ffaa7d7d7"

require linux-hardkernel.inc

O_KERNEL_CONFIG_odroid-n2-hardkernel  = "odroid-n2-hardkernel"

SRC_URI += "file://${O_KERNEL_CONFIG}/defconfig"


do_install_prepend() {
    bbnote "custom kernel_do_install customization"
    cp ${B}/arch/arm64/boot/dts/amlogic/*.dtb ${B}/arch/arm64/boot/dts/
}

COMPATIBLE_MACHINE = "(odroid-n2-hardkernel)"

