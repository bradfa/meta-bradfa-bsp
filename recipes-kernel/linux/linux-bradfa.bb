inherit kernel
require recipes-kernel/linux/linux-yocto.inc

PR = "r1"

# For your linux git repo, name a branch the same as your ${MACHINE} name and
# define the name of your kernel defconfig as ${MACHINE}.defconfig within the
# files/ directory.
KBRANCH = "${MACHINE}"
SRC_URI = " \
	git://${TOPDIR}/../linux/;protocol=file;nocheckout=1;name=machine \
	file://${MACHINE}.cfg \
	"

LINUX_VERSION ?= "0.0"
LINUX_VERSION_EXTENSION_append = "-custom"

SRCREV = "${AUTOREV}"

PV = "${LINUX_VERSION}+git${SRCPV}"

# Override COMPATIBLE_MACHINE to include your machine in a copy of this recipe
# file. Leaving it empty here ensures an early explicit build failure.
COMPATIBLE_MACHINE = "(^$)"
