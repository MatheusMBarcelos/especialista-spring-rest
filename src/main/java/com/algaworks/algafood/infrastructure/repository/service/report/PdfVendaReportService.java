package com.algaworks.algafood.infrastructure.repository.service.report;

import com.algaworks.algafood.domain.exception.ReportException;
import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.service.VendaQueryService;
import com.algaworks.algafood.domain.service.VendaReportService;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Locale;

@AllArgsConstructor
@Repository
public class PdfVendaReportService implements VendaReportService {

    private VendaQueryService vendaQueryService;

    @Override
    public byte[] consultarVendasDiariasPdf(VendaDiariaFilter filtro, String timeOffSet) {
        try {

            var inputStream = this.getClass().getResourceAsStream("/relatorios/vendas-diarias.jasper");

            var paramentros = new HashMap<String, Object>();

            paramentros.put("REPORT_LOCALE", new Locale("pt", "BR"));

            var vendasDiarias = vendaQueryService.consultarVendasDiarias(filtro, timeOffSet);

            var dataSource = new JRBeanCollectionDataSource(vendasDiarias);

            var jasperPrint = JasperFillManager.fillReport(inputStream, paramentros, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new ReportException("Não foi possível emitir o relatório de vendas diárias", e);
        }

    }
}
