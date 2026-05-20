// Keywords/utils/EvidenceReporter.groovy
package utils

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable

public class EvidenceReporter {

    // Menyimpan data semua step selama test berjalan
    static List<Map> stepList = []
    static String currentTestCaseId = ''
    static String screenshotDir = ''

    /**
     * Inisialisasi evidence untuk satu test case.
     * Panggil di awal setiap test case.
     */
    @Keyword
    static void initEvidence(String testCaseId) {
        stepList = []
        currentTestCaseId = testCaseId

        // Buat folder screenshot khusus test case ini
        screenshotDir = System.getProperty('user.dir') +
                        "/Reports/Evidence/${testCaseId}/"
        new File(screenshotDir).mkdirs()

        WebUI.comment("📋 Evidence initialized for: ${testCaseId}")
    }

    /**
     * Catat satu step: ambil screenshot + simpan ke list.
     * @param stepNo   - nomor urut step (1, 2, 3, ...)
     * @param action   - deskripsi aksi (misal: "Open Login Page")
     * @param status   - "PASS" atau "FAIL"
     */
    @Keyword
    static void captureStep(int stepNo, String action, String status) {
        String fileName = "step_${stepNo}.png"
        String filePath  = screenshotDir + fileName

        // Ambil screenshot
        WebUI.takeScreenshot(filePath)

        // Simpan data step ke list
        stepList << [
            step      : stepNo,
            action    : action,
            status    : status,
            imagePath : filePath
        ]

        WebUI.comment("📸 Step ${stepNo} captured: ${action} [${status}]")
    }

    /**
     * Kembalikan semua data step yang sudah dikumpulkan.
     */
    @Keyword
    static List<Map> getStepList() {
        return stepList
    }
}