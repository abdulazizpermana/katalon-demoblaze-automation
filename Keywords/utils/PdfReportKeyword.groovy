// Keywords/utils/PdfReportKeyword.groovy
package utils

import com.kms.katalon.core.annotation.Keyword

import com.itextpdf.text.BaseColor
import com.itextpdf.text.Document
import com.itextpdf.text.Element
import com.itextpdf.text.Font
import com.itextpdf.text.Image
import com.itextpdf.text.PageSize
import com.itextpdf.text.Paragraph
import com.itextpdf.text.Phrase
import com.itextpdf.text.Rectangle
import com.itextpdf.text.pdf.PdfPCell
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfWriter

// ⚠️ JANGAN import com.itextpdf.text.List — ini yang bikin konflik!
// Gunakan java.util.List secara fully qualified di method signature

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

public class PdfReportKeyword {

	@Keyword
	static void generateReport(
			String testCaseId,
			String scenario,
			String testerName,
			java.util.List<java.util.Map> steps) {   // ← fully qualified, bukan List<Map>

		String outputDir = System.getProperty('user.dir') + "/Reports/Evidence/"
		new File(outputDir).mkdirs()
		String pdfPath = outputDir + "${testCaseId}_Evidence.pdf"

		String execTime = LocalDateTime.now()
							.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))

		// ── Setup dokumen ──────────────────────────────────────────────
		Document doc = new Document(PageSize.A4, 40, 40, 50, 50)
		PdfWriter.getInstance(doc, new FileOutputStream(pdfPath))
		doc.open()

		// ── Font ───────────────────────────────────────────────────────
		Font fTitle  = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD,   BaseColor.WHITE)
		Font fHeader = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD,   new BaseColor(50, 50, 50))
		Font fValue  = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, new BaseColor(50, 50, 50))
		Font fTblHdr = new Font(Font.FontFamily.HELVETICA,  9, Font.BOLD,   BaseColor.WHITE)
		Font fTblVal = new Font(Font.FontFamily.HELVETICA,  9, Font.NORMAL, new BaseColor(30, 30, 30))
		Font fPass   = new Font(Font.FontFamily.HELVETICA,  9, Font.BOLD,   new BaseColor(0, 128, 0))
		Font fFail   = new Font(Font.FontFamily.HELVETICA,  9, Font.BOLD,   new BaseColor(200, 0, 0))

		// ── Warna ──────────────────────────────────────────────────────
		BaseColor colorPrimary = new BaseColor(30, 80, 160)
		BaseColor colorRowAlt  = new BaseColor(235, 242, 255)
		BaseColor colorBorder  = new BaseColor(180, 180, 180)

		// ── HEADER BANNER ──────────────────────────────────────────────
		PdfPTable banner = new PdfPTable(1)
		banner.setWidthPercentage(100)
		PdfPCell bannerCell = new PdfPCell(new Phrase("  AUTOMATION TEST EVIDENCE", fTitle))
		bannerCell.setBackgroundColor(colorPrimary)
		bannerCell.setPadding(12)
		bannerCell.setBorder(Rectangle.NO_BORDER)
		banner.addCell(bannerCell)
		doc.add(banner)
		doc.add(new Paragraph(" "))

		// ── INFO TABLE ─────────────────────────────────────────────────
		PdfPTable infoTable = new PdfPTable([30f, 70f] as float[])
		infoTable.setWidthPercentage(100)

		// Closure helper pakai java.util.Map explicitly
		def addInfoRow = { String label, String value ->
			PdfPCell lCell = new PdfPCell(new Phrase(label, fHeader))
			lCell.setPadding(6)
			lCell.setBorderColor(colorBorder)
			infoTable.addCell(lCell)

			PdfPCell vCell = new PdfPCell(new Phrase(value, fValue))
			vCell.setPadding(6)
			vCell.setBorderColor(colorBorder)
			infoTable.addCell(vCell)
		}

		addInfoRow("Test Case ID", testCaseId)
		addInfoRow("Scenario",     scenario)
		addInfoRow("Execution",    execTime)
		addInfoRow("Tester",       testerName)

		doc.add(infoTable)
		doc.add(new Paragraph(" "))

		// ── STEP TABLE ─────────────────────────────────────────────────
		PdfPTable stepTable = new PdfPTable([8f, 30f, 12f, 50f] as float[])
		stepTable.setWidthPercentage(100)

		['STEP', 'ACTION', 'STATUS', 'SCREENSHOT'].each { String h ->
			PdfPCell hCell = new PdfPCell(new Phrase(h, fTblHdr))
			hCell.setBackgroundColor(colorPrimary)
			hCell.setPadding(8)
			hCell.setHorizontalAlignment(Element.ALIGN_CENTER)
			hCell.setBorderColor(colorBorder)
			stepTable.addCell(hCell)
		}

		// Iterasi pakai index manual — hindari eachWithIndex agar tidak ada konflik closure
		int idx = 0
		for (java.util.Map s : steps) {        // ← java.util.Map explicitly
			BaseColor rowColor = (idx % 2 == 0) ? BaseColor.WHITE : colorRowAlt

			// STEP
			PdfPCell stepCell = new PdfPCell(new Phrase("${s.step}", fTblVal))
			stepCell.setBackgroundColor(rowColor)
			stepCell.setPadding(6)
			stepCell.setHorizontalAlignment(Element.ALIGN_CENTER)
			stepCell.setVerticalAlignment(Element.ALIGN_MIDDLE)
			stepCell.setBorderColor(colorBorder)
			stepTable.addCell(stepCell)

			// ACTION
			PdfPCell actionCell = new PdfPCell(new Phrase("${s.action}", fTblVal))
			actionCell.setBackgroundColor(rowColor)
			actionCell.setPadding(6)
			actionCell.setVerticalAlignment(Element.ALIGN_MIDDLE)
			actionCell.setBorderColor(colorBorder)
			stepTable.addCell(actionCell)

			// STATUS
			Font statusFont = ("${s.status}" == 'PASS') ? fPass : fFail
			PdfPCell statusCell = new PdfPCell(new Phrase("${s.status}", statusFont))
			statusCell.setBackgroundColor(rowColor)
			statusCell.setPadding(6)
			statusCell.setHorizontalAlignment(Element.ALIGN_CENTER)
			statusCell.setVerticalAlignment(Element.ALIGN_MIDDLE)
			statusCell.setBorderColor(colorBorder)
			stepTable.addCell(statusCell)

			// SCREENSHOT
			PdfPCell imgCell
			File imgFile = new File("${s.imagePath}")
			if (imgFile.exists()) {
				Image img = Image.getInstance(imgFile.absolutePath)
				img.scaleToFit(250, 150)
				imgCell = new PdfPCell(img, false)
				imgCell.setPadding(4)
			} else {
				imgCell = new PdfPCell(new Phrase("[Screenshot not found]", fTblVal))
				imgCell.setPadding(6)
			}
			imgCell.setBackgroundColor(rowColor)
			imgCell.setBorderColor(colorBorder)
			imgCell.setVerticalAlignment(Element.ALIGN_MIDDLE)
			stepTable.addCell(imgCell)

			idx++
		}

		doc.add(stepTable)

		// ── FOOTER ─────────────────────────────────────────────────────
		doc.add(new Paragraph(" "))
		Paragraph footer = new Paragraph(
			"Generated automatically by Katalon Studio — ${execTime}",
			new Font(Font.FontFamily.HELVETICA, 8, Font.ITALIC, new BaseColor(120, 120, 120)))
		footer.setAlignment(Element.ALIGN_CENTER)
		doc.add(footer)

		doc.close()

		// ⚠️ Ganti WebUI.comment dengan println agar aman di static context
		println("✅ PDF report generated: ${pdfPath}")
	}
}