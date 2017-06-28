package test.rest_practice;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import test.rest_practice.cache.Cache;
import test.rest_practice.model.Record;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class RecordControllerTest {

  private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
      MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

  private MockMvc mockMvc;

  private String userName = "bdussault";

  private HttpMessageConverter mappingJackson2HttpMessageConverter;

  private List<Record> recordList = new ArrayList<>();

  private Record record;

  @Autowired
  private Cache cache;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Autowired
  void setConverters(HttpMessageConverter<?>[] converters) {

    this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
        .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().orElse(null);

    assertNotNull("the JSON message converter must not be null",
        this.mappingJackson2HttpMessageConverter);
  }

  @Before
  public void setup() throws Exception {
    this.mockMvc = webAppContextSetup(webApplicationContext).build();
    Record record1 = new Record("aba", Boolean.TRUE, new Date());
    Record record2 = new Record("bac", Boolean.FALSE, new Date());
    this.record = record1;

    recordList.add(record1);
    recordList.add(record2);

    cache.saveRecord(record1);
    cache.saveRecord(record2);

  }

  @Test
  public void getSingleRecord() throws Exception {
    mockMvc.perform(get("/record/" + this.recordList.get(0).getValue())).andExpect(status().isOk())
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.value", is(this.recordList.get(0).getValue())))
        .andExpect(jsonPath("$.isPalindrome", is(true)));
  }

  @Test
  public void getRecords() throws Exception {
    mockMvc.perform(get("/getRecords?start=0&&offset=10"))
        .andExpect(status().isOk()).andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.[0].value", is(this.recordList.get(0).getValue())))
        .andExpect(jsonPath("$.[0].isPalindrome", is(true)));
  }

  @Test
  public void addRecord() throws Exception {
    String recordJson = json(new Record(this.record.getValue(), Boolean.TRUE, new Date()));

    this.mockMvc.perform(post("/addRecord").contentType(contentType).content(recordJson))
        .andExpect(status().isOk()).andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.value", is(this.recordList.get(0).getValue())))
        .andExpect(jsonPath("$.isPalindrome", is(true)));
  }

  protected String json(Object o) throws IOException {
    MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
    this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON,
        mockHttpOutputMessage);
    return mockHttpOutputMessage.getBodyAsString();
  }
}
