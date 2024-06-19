package com.chan.grpc_callpy.controller;

import com.chan.grpc_callpy.entity.Cad;
import com.chan.grpc_callpy.mapper.CadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Author dam
 * @create 2024/1/18 20:37
 */
@RestController
@RequestMapping("/cad")
public class CadController {

    private CadMapper cadMapper;
    @Autowired
    public void setCadMapper(CadMapper cadMapper) {
        this.cadMapper = cadMapper;
    }

    /**
     * 增添数据
     */
    @GetMapping("/insert")
    public Object insert() {
        Cad cad = ApiController.getCad();
        if (cad == null) {
            return "No content found, please execute the script first";
        }
        return cadMapper.insert(cad);
    }

    /**
     * 查询数据
     */
    @GetMapping("/show")
    public Object show() {
        return cadMapper.selectList(null);
    }

    /**
     * 删除数据
     */
    @DeleteMapping("/delete")
    public Object delete(Integer id) {
        return cadMapper.deleteById(id);
    }

    /**
     * 修改数据
     */
    @GetMapping("/update")
    public Object update(Integer id, String content) {
        Cad cad = new Cad();
        cad.setId(id);
        cad.setContent(content);
        return cadMapper.updateById(cad);
    }
}


