package test.scyscanner.com.scyscannertest.application.api

import java.net.NetworkInterface
import java.util.*


interface DeviceIpProvider {

    fun provideIpAddress(useIPv4: Boolean = true): String

}

class DeviceIpProviderImpl : DeviceIpProvider {

    override fun provideIpAddress(useIPv4: Boolean): String {
        try {
            val interfaces = Collections.list(NetworkInterface.getNetworkInterfaces())
            for (intf in interfaces) {
                val addrs = Collections.list(intf.inetAddresses)
                for (addr in addrs) {
                    if (!addr.isLoopbackAddress) {
                        val sAddr = addr.hostAddress
                        val isIPv4 = sAddr.indexOf(':') < 0
                        if (useIPv4) {
                            if (isIPv4)
                                return sAddr
                        } else {
                            if (!isIPv4) {
                                val delim = sAddr.indexOf('%')
                                return if (delim < 0) sAddr.toUpperCase() else sAddr.substring(0, delim).toUpperCase()
                            }
                        }
                    }
                }
            }
        } catch (ex: Exception) {
            throw IllegalArgumentException("can't get IP address")
        }
        throw IllegalArgumentException("can't get IP address")
    }

}