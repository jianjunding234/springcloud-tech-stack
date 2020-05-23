package com.dingjianjun.productservice.controller;

import com.dingjianjun.productservice.dto.ProductDto;
import com.dingjianjun.productservice.dto.RespDto;
import com.dingjianjun.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Jianjun.Ding
 * @description:
 * @date 2020/5/23
 */
@RequestMapping("/product")
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/hi")
    public String test() {
        return "hi";
    }

    @PostMapping("/add")
    public RespDto<Boolean> add(@RequestBody ProductDto record) {
        return productService.add(record);
    }

    @GetMapping("/reductStock")
    public RespDto<Boolean> reductStock(@RequestParam(value = "productNo") String productNo,
                                        @RequestParam(value = "amount") Integer amount) {
        return productService.update(productNo, amount);
    }
}
