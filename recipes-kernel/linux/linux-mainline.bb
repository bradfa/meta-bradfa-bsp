LINUX_VERSION ?= "4.6"
PR = "r2"
PV = "${LINUX_VERSION}+git${SRCPV}"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel
require recipes-kernel/linux/linux-dtb.inc

KBRANCH = "linux-${LINUX_VERSION}.y"
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;branch=${KBRANCH}"
SRCREV = "${AUTOREV}"

RDEPENDS_kernel-base += "kernel-devicetree"

# Override COMPATIBLE_MACHINE to include your machine in a copy of this recipe
# file. Leaving it empty here ensures an early explicit build failure.
COMPATIBLE_MACHINE = "(^$)"
COMPATIBLE_MACHINE_bbb = "bbb"

S = "${WORKDIR}/git"

# Use an in-tree defconfig defined by the machine's conf
do_configure() {
	cp ${S}/arch/${ARCH}/configs/${KBUILD_DEFCONFIG} ${B}/.config
	yes '' | oe_runmake -C ${S} O=${B} oldconfig
}

# The patched archiver only works correctly for linux-yocto based kernels
ARCHIVER_MODE[src] = "original"
