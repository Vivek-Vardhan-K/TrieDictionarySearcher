package cpp.searcher.triedictionarysearch;

import cpp.searcher.triedictionarysearch.model.TrieNode;
import cpp.searcher.triedictionarysearch.service.ConstructService;
import cpp.searcher.triedictionarysearch.service.FinderService;
import cpp.searcher.triedictionarysearch.ui.SwingSuggestor;

import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

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
    
    public static void main(String[] args) throws FileNotFoundException {
        SpringApplication.run(TrieDictionarySearchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.setProperty("java.awt.headless", "false");
        TrieNode root=new TrieNode();
        constructService.constructTrie(root,"words.txt");
        JFrame frame = new JFrame();
         frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         frame.getContentPane().add(new SwingSuggestor(root));
         frame.pack();
         frame.setLocationRelativeTo(null);
         frame.setVisible(true);
    }
}
