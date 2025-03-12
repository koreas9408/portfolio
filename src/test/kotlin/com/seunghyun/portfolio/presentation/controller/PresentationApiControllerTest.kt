package com.seunghyun.portfolio.presentation.controller

import org.assertj.core.api.Assertions.assertThat
import org.json.JSONArray
import org.json.JSONObject
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import java.nio.charset.StandardCharsets
import kotlin.test.Test

// Springboot를 실제로 띄우고 테스트한다.
// 프로젝트를 실행하는 것과 동일하기 때문에 시간이 오래걸린다는 단점이 존재한다.
// 테스트 코드는 정말 중요하고, 테스트 코드 작성은 다양한 방법들이 있으니 각 회사, 팀 상황에 맞게 어떻게 사용할지 고민해보는 것이 중요!
@SpringBootTest
@AutoConfigureMockMvc // MockMVC 자동 세팅
@DisplayName("[API 컨트롤러 테스트]") // 테스트 이름 설정 (클래스/메서드 가능)
class PresentationApiControllerTest(
    @Autowired private val mockMvc: MockMvc
) {

    @Test
    @DisplayName("Introductions 조회")
    fun testGetIntroductions() {

        // given
        val uri = "/api/v1/introductions"

        // when
        val mvcResult = performGet(uri)
        val contentAsString = mvcResult.response.getContentAsString(StandardCharsets.UTF_8)
        val jsonArray = JSONArray(contentAsString)

        // then
        assertThat(jsonArray.length()).isPositive()
    }

    @Test
    @DisplayName("Links 조회")
    fun testGetLinks() {

        // given
        val uri = "/api/v1/links"

        // when
        val mvcResult = performGet(uri)
        val contentAsString = mvcResult.response.getContentAsString(StandardCharsets.UTF_8)
        val jsonArray = JSONArray(contentAsString)

        // then
        assertThat(jsonArray.length()).isPositive()
    }

    @Test
    @DisplayName("Resume 조회")
    fun testGetResume() {

        // given
        val uri = "/api/v1/resume"

        // when
        val mvcResult = performGet(uri)
        val contentAsString = mvcResult.response.getContentAsString(StandardCharsets.UTF_8)
        val jsonObject = JSONObject(contentAsString)

        // then
        assertThat(jsonObject.optJSONArray("experiences").length()).isPositive()
        assertThat(jsonObject.optJSONArray("achievements").length()).isPositive()
        assertThat(jsonObject.optJSONArray("skills").length()).isPositive()
    }

    @Test
    @DisplayName("Projects 조회")
    fun testGetProjects() {

        // given
        val uri = "/api/v1/projects"

        // when
        val mvcResult = performGet(uri)
        val contentAsString = mvcResult.response.getContentAsString(StandardCharsets.UTF_8)
        val jsonArray = JSONArray(contentAsString)

        // then
        assertThat(jsonArray.length()).isPositive()
    }

    private fun performGet(uri: String): MvcResult {
        return mockMvc.perform(MockMvcRequestBuilders.get(uri))
            .andDo(MockMvcResultHandlers.print())
            .andReturn()
    }
}