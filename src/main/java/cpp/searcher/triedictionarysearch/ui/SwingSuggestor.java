package cpp.searcher.triedictionarysearch.ui;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.naming.java.javaURLContextFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cpp.searcher.triedictionarysearch.model.TrieNode;
import cpp.searcher.triedictionarysearch.service.ConstructService;
import cpp.searcher.triedictionarysearch.service.FinderService;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

import javax.swing.*;

public class SwingSuggestor extends JPanel{
    JComboBox<String> jBox=new JComboBox<>();
    private final JTextField tf;

    @Autowired
    public FinderService finderService;

    private TrieNode root;

    public SwingSuggestor(TrieNode node) throws FileNotFoundException{
        super(new BorderLayout());
        root=node;
        
        jBox.setEditable(true);
        tf=(JTextField) jBox.getEditor().getEditorComponent();
        tf.addKeyListener(new KeyAdapter(){

        });
        tf.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        String text = tf.getText();
                        if(text.length()==0) {
                            jBox.hidePopup();
                        }
                        else{
                            DefaultComboBoxModel m = getSuggestedModel(text);
                            if(m.getSize()==0) {
                                jBox.hidePopup();
                            }
                            else{
                                setModel(m, text);
                                jBox.showPopup();
                            }
                        }
                    }
                });

            }
            public void keyPressed(KeyEvent e) {
                String text = tf.getText();
                if(e.getKeyCode()==(KeyEvent.VK_BACK_SPACE)){
                    return;
                }
                setModel(getSuggestedModel(text), text);
            }
        });
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(BorderFactory.createTitledBorder("Trie Suggestion Box"));
        p.add(jBox, BorderLayout.NORTH);
        add(p);
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        setPreferredSize(new Dimension(300, 150));
    }

    private void setModel(DefaultComboBoxModel mdl, String str) {
        jBox.setModel(mdl);
        jBox.setSelectedIndex(-1);
        tf.setText(str);
    }
    private DefaultComboBoxModel getSuggestedModel(String text) {
        DefaultComboBoxModel m = new DefaultComboBoxModel();
        List<String> suggestions=finderService.startsWith(text,root);
        suggestions.forEach(str->m.addElement(str));
        return m;
    }
}
        