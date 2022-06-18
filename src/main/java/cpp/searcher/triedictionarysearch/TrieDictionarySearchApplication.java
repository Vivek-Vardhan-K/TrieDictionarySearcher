package cpp.searcher.triedictionarysearch;

import cpp.searcher.triedictionarysearch.model.TrieNode;
import cpp.searcher.triedictionarysearch.service.ConstructService;
import cpp.searcher.triedictionarysearch.service.FinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrieDictionarySearchApplication implements CommandLineRunner {
    @Autowired
    public ConstructService constructService;
    @Autowired
    public FinderService finderService;
    public static void main(String[] args) {
        SpringApplication.run(TrieDictionarySearchApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        TrieNode root=new TrieNode();
        constructService.constructTrie(root,"words.txt");
        for(String word: finderService.startsWith("insta",root)){
            System.out.println(word);
        }
    }
}
