package com.mc.spring.restws.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import com.mc.spring.restws.model.Billionaires;
import com.mc.spring.restws.repository.BillionairesRepository;
import com.mc.spring.restws.service.impl.BillionairesServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class DataServiceTest {

    private List<Billionaires> billionairesList = new ArrayList<Billionaires>();

    @Before
    public void init() {
        billionairesList.clear();
        Billionaires f1 = new Billionaires();
        f1.setId(1L);
        f1.setFirst_name("f1");
        f1.setLast_name("l1");
        f1.setCareer("c1");
        billionairesList.add(f1);
        Billionaires f2 = new Billionaires();
        f2.setId(2L);
        f2.setFirst_name("f2");
        f2.setLast_name("l2");
        f2.setCareer("c2");
        billionairesList.add(f2);
    }

    private BillionairesRepository getMockSetup() {
        // mock setup
        BillionairesRepository repository = Mockito.mock(BillionairesRepository.class);
        Mockito.when(repository.findAll()).thenReturn(billionairesList);
        Mockito.when(repository.findById(anyLong())).thenAnswer(
                new Answer<Optional<Billionaires>>() {
                    @Override
                    public Optional<Billionaires> answer(InvocationOnMock invocationOnMock) throws Throwable {
                        Long id = (Long) invocationOnMock.getArgument(0);
                        return billionairesList.stream().filter(b -> b.getId() == id).findFirst();
                    }
                }
        );
        return repository;
    }

    @Test
    public void testDataServices() {
        BillionairesServiceImpl service = new BillionairesServiceImpl();
        service.setBillionairesRepository(getMockSetup());
        assertTrue(service.getAllBillionaires().size() == 2);
        assertNotNull(service.getBillionaires(1L));
        assertTrue(service.getBillionaires(2L).getFirst_name().equals("f2"));
    }

    @Test(expected = NoSuchElementException.class)
    public void testDataServiceException() {
        BillionairesServiceImpl service = new BillionairesServiceImpl();
        service.setBillionairesRepository(getMockSetup());
        service.getBillionaires(3L);
        fail();
    }
}
