package co.istad.photostad.api.watermark;

import co.istad.photostad.api.watermark.web.CreateWatermarkDto;
import co.istad.photostad.api.watermark.web.WatermarkDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class WatermarkServiceImp implements WatermarkService {
    private final WatermarkMapper watermarkMapper;
    private final WatermarkMapStruct watermarkMapStruct;

    @Override
    public WatermarkDto insertWatermark(CreateWatermarkDto createWatermarkDto) {
        Watermark watermark = watermarkMapStruct.createWatermarkDtoToWatermark(createWatermarkDto);
        watermark.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        watermark.setUuid(UUID.randomUUID().toString());
        if (watermarkMapper.insert(watermark)) {
            return this.selectWatermarkById(watermark.getId());
        }
        throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "watermark insert has been fail"
        );
    }

    @Override
    public WatermarkDto selectWatermarkById(Integer id) {
        Watermark watermark = watermarkMapper.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("watermark with %d is not found", id)
                )
        );
        return watermarkMapStruct.watermarkToWatermarkDto(watermark);
    }

    @Override
    public Integer deleteWatermarkById(Integer id) {
        if (watermarkMapper.isWatermarkIdExist(id)) {
            if (watermarkMapper.deleteById(id)) {
                return id;
            }
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("delete watermark with %d is fail", id)
            );
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("watermark with %d is not found", id)
        );
    }

    @Override
    public PageInfo<WatermarkDto> selectAllWatermark(int page, int limit) {
        PageInfo<Watermark> watermarkPageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(
                () -> watermarkMapper.findAll()
        );
        return watermarkMapStruct.watermarkPageinfoToWatermarkDtoPageInfo(watermarkPageInfo);
    }

    @Override
    public WatermarkDto updateWatermark(Integer id, CreateWatermarkDto createWatermarkDto) {
        if (watermarkMapper.isWatermarkIdExist(id)) {
            Watermark watermark=watermarkMapStruct.createWatermarkDtoToWatermark(createWatermarkDto);
            watermark.setId(id);
            if (watermarkMapper.update(watermark)) {
                return this.selectWatermarkById(id);
            }
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("update watermark with id %d is fail", id)
            );
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("watermark with %d is not found", id)
        );
    }


}
