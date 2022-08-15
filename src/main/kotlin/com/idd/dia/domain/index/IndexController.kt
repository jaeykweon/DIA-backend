package com.idd.dia.domain.index

import com.idd.dia.domain.ApiResponse
import com.idd.dia.domain.index.dto.IndexResponseData
import com.idd.dia.domain.index.service.IndexQueryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class IndexController(
    private val indexQueryService: IndexQueryService
) {

    @GetMapping("/indexes")
    fun getIndexList():ApiResponse<List<IndexResponseData>> {
        val indexList = indexQueryService.getIndexList()
        return ApiResponse.success(indexList)
    }

}