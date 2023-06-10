package co.istad.photostad.api.watermark;

import co.istad.photostad.api.certificate.web.CertificateDto;
import co.istad.photostad.api.watermark.web.CreateWatermarkDto;
import co.istad.photostad.api.watermark.web.WatermarkDto;
import com.github.pagehelper.PageInfo;

public interface WatermarkService {
    WatermarkDto insertWatermark(CreateWatermarkDto createWatermarkDto);
    WatermarkDto selectWatermarkById(Integer id);
    Integer deleteWatermarkById(Integer id);
    PageInfo<WatermarkDto> selectAllWatermark(int page,int limit);
    WatermarkDto updateWatermark(Integer id, CreateWatermarkDto createWatermarkDto);
}
