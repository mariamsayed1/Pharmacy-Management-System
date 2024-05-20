package com.example.aswe.demo.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.aswe.demo.Models.Category;
import com.example.aswe.demo.Models.Pharmacist;
import com.example.aswe.demo.Models.Product;
import com.example.aswe.demo.Models.UserLog;
import com.example.aswe.demo.Repositories.CategoryRepository;
import com.example.aswe.demo.Repositories.PharmacistRepository;
import com.example.aswe.demo.Repositories.ProductRepository;
import com.example.aswe.demo.Repositories.UserLogRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/Admin")
public class AdminController {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private PharmacistRepository pharmacistRepository;
    
    @Autowired
    private UserLogRepository userLogRepository;


    // private static final List<String> ALLOWED_MIME_TYPES = Arrays.asList("image/jpeg", "image/jpg", "image/png", "image/webp");

    String directory = "C:/Users/Prof. Mahmoud Sayed/Desktop/Pharmacy-Management-System/demo/src/main/resources/static/IMAGES/";

   
    @GetMapping("")
    public ModelAndView adminIndex() {         
        return new ModelAndView("admin.html");
    }


    @GetMapping("categories")
    public ModelAndView getAllCategories() {
        ModelAndView mav = new ModelAndView("listCategory.html");
        List<Category> categories = this.categoryRepository.findAll();
        mav.addObject("categories", categories);
        return mav;
    }

    @GetMapping("addCategory")
    public ModelAndView addCategories() {
        ModelAndView mav = new ModelAndView("addCategory.html");
        Category newCategory = new Category();
        mav.addObject("category", newCategory);
        return mav;
    }
    @PostMapping("addCategory")
    public ModelAndView saveCategory(@ModelAttribute Category category) {
        if(!category.isEmpty(category.getName()) && !category.isEmpty(category.getImage()) ){
            this.categoryRepository.save(category);
        }
        return new ModelAndView("redirect:/Admin/addCategory");
    }

    @GetMapping("editCategory/{id}")
    public ModelAndView editCategory(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView("editCategory.html");
        Category category = this.categoryRepository.findById(id); 
        mav.addObject("category", category);
        return mav;
    }
    @PostMapping("editCategory/{id}")
    public ModelAndView editCategory(@PathVariable("id") int id, @ModelAttribute Category updatedCategory) {
        Category existingCategory = this.categoryRepository.findById(id);
        existingCategory.setName(updatedCategory.getName());
        existingCategory.setImage(updatedCategory.getImage());
        categoryRepository.save(existingCategory); 
        return new ModelAndView("redirect:/Admin/categories"); 
    }

    @GetMapping("deleteCategory/{id}")
    public ModelAndView deleteCategory(@PathVariable("id") int id) {
        this.categoryRepository.deleteById(id);
        return new ModelAndView("redirect:/Admin/categories");
    }

    @GetMapping("products")
    public ModelAndView getAllProducts(@RequestParam(defaultValue = "1") int page) {
        ModelAndView mav = new ModelAndView("products.html");
        Page<Product> products = this.productRepository.findAll(PageRequest.of(page-1, 3));

        int totalPages = products.getTotalPages();

        // List<Product> products = this.productRepository.findAll();
        mav.addObject("products", products);
        mav.addObject("currentPage", page);
        mav.addObject("totalPages", totalPages); 
        return mav;
    }

    @GetMapping("addProduct")
    public ModelAndView addProduct() {
        ModelAndView mav = new ModelAndView("addProduct.html");
        List<Category> allCategories = this.categoryRepository.findAll();
        mav.addObject("allCategories", allCategories);
        Product newProduct = new Product();
        mav.addObject("product", newProduct);
        return mav;
    }
    @PostMapping("addProduct")
    public ModelAndView saveProduct(@ModelAttribute Product product, @RequestParam ("image") String image) {
        // if(!product.isEmpty(product.getName()) && !product.isEmpty(product.getImage()) && (product.getPrice()>0)){
            // this.productRepository.save(product);
        // }

        ModelAndView mav = new ModelAndView("addProduct.html");

        // byte[] contentBytes = product.getImage().getBytes();
            
        // Create a MockMultipartFile with the byte array
        // MultipartFile image = new MockMultipartFile(product.getImage(), contentBytes);

        // String contentType = image.getContentType();

        // if(!product.isEmpty(product.getName())){
        //     mav.addObject("nameError", "Invalid email format");
        //         mav.addObject("hasNameError", true);
        // }
        // if(product.isEmpty(product.getActiveIngredient())){
        //     mav.addObject("emailError", "Invalid email format");
        //         mav.addObject("hasEmailError", true);
        // }
        if(!product.isEmpty(product.getName()) && !product.isEmpty(product.getActiveIngredient())){
            if(!product.isPriceValid(product.getPrice())){
                mav.addObject("priceError", "Invalid input: Number must be greater than 0.");
                mav.addObject("hasPriceError", true);
            }
            if(!product.isQuantityValid(product.getQuantity())){
                mav.addObject("quantityError", "Invalid input: Number must be greater than 0.");
                mav.addObject("hasQuantityError", true);
            }
            if(!product.isValidDate(product.getProdDate(), product.getExpDate())){
                mav.addObject("dateError", "Invalid input: Expiry Date must be after Production Date");
                mav.addObject("hasDateError", true);
            }
            // if (ALLOWED_MIME_TYPES.contains(contentType)) {

            //     String imageName = image.getOriginalFilename();
            //     Path imagePath = Paths.get(directory).resolve(imageName);
            //     System.out.println("File Path: " + imagePath.toString());
            //     if(!Files.exists(imagePath)){
            //         try {
            //                 Files.write(imagePath, image.getBytes());
            //                 System.out.println("File uploaded successfully: " + imagePath.toString());

            //             } catch (IOException e) {
            //                 System.out.println("Error saving file: " + e.getMessage());
            //             }
            //     }else{
            //         System.out.println("The image exists in the Images folder" );
            //     }
            // }else{
            //     mav.addObject("imageError", "Invalid file type. Only JPEG, JPG, PNG, and WEBP files are allowed.");
            //     mav.addObject("hasImageError", true);
            // }

                // byte[] decodedBytes = Base64.getDecoder().decode(image);
        
                // // You can generate a unique name for the file or use a fixed name
                // // String imageName = image.getOriginalFilename();

                // Path imagePath = Paths.get(directory).resolve(image);

                // if(!Files.exists(imagePath)){
                //     // Write the byte array to a file
                //     try (FileOutputStream fos = new FileOutputStream(imagePath.toFile())) {
                //         fos.write(decodedBytes);
                //         System.out.println("File uploaded successfully: " + imagePath.toString());
                //     }catch (IOException e) {
                //         System.out.println("Error saving file: " + e.getMessage());
                //     }
                // }else{
                //     System.out.println("The image exists in the Images folder" );
                // }



            if (mav.getModel().containsKey("hasPriceError") || mav.getModel().containsKey("hasQuantityError") || mav.getModel().containsKey("hasDateError") || mav.getModel().containsKey("hasImageError")) 
                return mav;
        }

        this.productRepository.save(product);
        return new ModelAndView("redirect:/Admin/addProduct");
    }

    @GetMapping("editProduct/{id}")
    public ModelAndView editProduct(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView("editProduct.html");
        Product product = this.productRepository.findById(id); 
        List<Category> allCategories = this.categoryRepository.findAll();
        mav.addObject("allCategories", allCategories);
        mav.addObject("product", product);
        return mav;
    }
    @PostMapping("editProduct/{id}")
    public ModelAndView editProduct(@PathVariable("id") int id, @ModelAttribute Product updatedProduct) {
        Product existingProduct = this.productRepository.findById(id);
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setImage(updatedProduct.getImage());
        existingProduct.setActiveIngredient(updatedProduct.getActiveIngredient());
        existingProduct.setSideEffect(updatedProduct.getSideEffect());
        existingProduct.setQuantity(updatedProduct.getQuantity());
        existingProduct.setCategory(updatedProduct.getCategory());

        productRepository.save(existingProduct); 
        return new ModelAndView("redirect:/Admin/products"); 
    }

    @GetMapping("deleteProduct/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") int id) {
        this.productRepository.deleteById(id);
        return new ModelAndView("redirect:/Admin/products");
    }
    @GetMapping("addPharmacist")
    public ModelAndView addPharmacist() {
        ModelAndView mav = new ModelAndView("addPharmacist.html");
        Pharmacist newPharmacist = new Pharmacist();
        mav.addObject("pharmacist", newPharmacist);
        return mav;
    }
    @PostMapping("addPharmacist")
    public ModelAndView savePharmacist(@ModelAttribute Pharmacist pharmacist) {
        this.pharmacistRepository.save(pharmacist);
        return new ModelAndView("redirect:/Admin/addPharmacist");
    }
    @GetMapping("/logs")
    public ModelAndView getAllUserLogs() {
    ModelAndView mav = new ModelAndView("logs.html");
    List<UserLog> userLogs = this.userLogRepository.findAll();
    mav.addObject("Logs", userLogs);
    return mav;
    }
}
