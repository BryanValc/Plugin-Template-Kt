package com.bryanvalc.pluginTemplateKt.common.module


import com.bryanvalc.pluginTemplateKt.common.config.LicenseConfig
import dev.respark.licensegate.LicenseGate

class License(
    private val config: LicenseConfig
) {

    fun validate(): Boolean {
        val publicKey = "-----BEGIN PUBLIC KEY----- YourPublicKeyFromLicenseGate -----END PUBLIC KEY-----";
        val licenseGate = LicenseGate("yourId", publicKey)

        val isValid = licenseGate.verify(config.key, config.scope).isValid

        return isValid
    }

}