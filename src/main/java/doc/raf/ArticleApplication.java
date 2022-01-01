package doc.raf;

import doc.raf.dao.ProduitReposotory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArticleApplication implements CommandLineRunner {
    @Autowired
    private ProduitReposotory prodRepo;

    public static void main(String[] args) {
        SpringApplication.run(ArticleApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
    //    prodRepo.save(new Produits(null,"PC Lenovo",2200,10));
    //     prodRepo.save(new Produits(null,"PC Acer",8200,150));
    //    prodRepo.save(new Produits(null,"DVD 4",2400,4));
    //     prodRepo.save(new Produits(null,"Radio",2500,20));
    //    prodRepo.save(new Produits(null,"LG G4",220,11));
    //    prodRepo.save(new Produits(null,"PC AZUS",7200,20));
    //    prodRepo.save(new Produits(null,"PC HP",8520,14));
    //    prodRepo.save(new Produits(null,"Iphone 10",5200,15));
    //    prodRepo.save(new Produits(null,"Samsung SA10",2400,50));
    //    prodRepo.save(new Produits(null,"Abdouraouf",1200,10));

        prodRepo.findAll().forEach(prod -> {
            //System.out.println(prod.getDesignationProd());
        });
    }
}
