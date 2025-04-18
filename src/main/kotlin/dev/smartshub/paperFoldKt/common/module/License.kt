package dev.smartshub.paperFoldKt.common.module


import dev.smartshub.paperFoldKt.common.config.LicenseConfig
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