import com.upgrad.eshop.UpgradEShopApplication;
import com.upgrad.eshop.dtos.ProductRequest;
import com.upgrad.eshop.entities.Product;
import com.upgrad.eshop.exceptions.ProductNotFoundException;
import com.upgrad.eshop.services.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UpgradEShopApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EshopTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Mock
    ProductService productService;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void newProductTest() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setCategory("Leather");
        productRequest.setDescription("It's Good");
        productRequest.setImage_url("image1.png");
        productRequest.setManufacturer("abc");
        productRequest.setName("BlueBag");
        productRequest.setCreated(new Date("2017-10-10"));
        productRequest.setUpdated(new Date("2017-10-10"));
        Mockito.when(productService.addProduct(Mockito.any())).thenReturn(null);
        ResponseEntity response = restTemplate.postForEntity(getRootUrl() + "/eshop_app/", productRequest, ProductRequest.class);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getBody());
    }


    @Test
    public void removeProduct()  {
        try {
            Mockito.when(productService.deleteProduct(Mockito.anyInt())).thenReturn(true);
        } catch ( ProductNotFoundException e) {
            e.printStackTrace();
        }
        ResponseEntity response = restTemplate.getForEntity(getRootUrl() + "/eshop_app/", ProductRequest.class);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getBody());
    }




}
