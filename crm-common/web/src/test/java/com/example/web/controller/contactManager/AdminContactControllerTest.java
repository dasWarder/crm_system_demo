//package com.example.web.controller.contactManager;
//
//import com.example.mapper.ContactMapper;
//import com.example.web.controller.AbstractContextController;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.jdbc.Sql;
//
//@Slf4j
//@WithMockUser(username = "test@gmail.com", authorities = "USER")
//@Sql(scripts = {"classpath:db/contactManager/populate_contact.sql"})
//public class AdminContactControllerTest extends AbstractContextController {
//
//    @Autowired
//    private ContactMapper contactMapper;
//
//    private static final String BASE_URL = "http://localhost:8080/admin/manage/contacts";
//
//    @Test
//    public void shouldReturnStatusOkAndPageOfAllContactsProperly() throws Exception {
//
//        log.info("Test findAllContacts() method");
//
//    }
//
//    @Test
//    public void shouldReturnStatusOkAndPageOfAllContactsFilteredByParamProperly() throws Exception {
//
//        log.info("Test findAllByParam() method");
//
//    }
//
//    @Test
//    public void shouldReturnStatusIsCreatedAndSaveContactProperly() throws Exception {
//
//        log.info("Test saveContact() method");
//
//    }
//
//    @Test
//    public void shouldReturnStatusOkAndUpdateContactProperly() throws Exception {
//
//        log.info("Test updateContactByEmail() method");
//
//    }
//
//    @Test
//    public void shouldReturnStatusOkAndGetContactByEmailProperly() throws Exception {
//
//        log.info("Test getContactByEmail() method");
//
//    }
//
//    @Test
//    public void shouldReturnStatusOkAndDeleteContactByEmailProperly() throws Exception {
//
//        log.info("Test deleteContactByEmail() method");
//
//
//    }
//}
