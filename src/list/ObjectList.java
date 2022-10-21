/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package list;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NhiLamHet
 * @param <A>
 */
public abstract class ObjectList<A extends IObject> extends ArrayList<A> {

    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public final void setFilePath(String filePath) {
        if (filePath != null && !filePath.isEmpty()) {
            this.filePath = filePath;
        }
    }

    public ObjectList() {
    }

    public ObjectList(String filePath) {
        setFilePath(filePath);
    }

    public boolean load() {
        try (Scanner sc = new Scanner(new File(this.filePath))) {
            A obj;
            String data;
            while (sc.hasNextLine()) {
                data = sc.nextLine();
                if (!data.isEmpty()) {
                    obj = parseString(data);
                    if (obj != null) {
                        add(obj);
                    }
                }
            }
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProductList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (A e : this) {
                writer.append(e.toString());
                writer.append("\n");
            }
            return true;

        } catch (IOException ex) {
            Logger.getLogger(ObjectList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<A> filterByName(String name) {
        List<A> resultList = new ArrayList();
        for (A o : this) {
            if (o.getName().contains(name)) {
                resultList.add(o);
            }
        }
        return resultList;
    }
    
    @Override
    public int indexOf(Object o) {
        int idx = -1;
        for (int i = 0; i < size(); i++) {
            if (((A) o).getName().equalsIgnoreCase(get(i).getName())) {
                idx = i;
                break;
            }
        }
        return idx;
    }

    @Override
    public boolean add(A e) {
        int idx = indexOf(e);
        if (idx >= 0) {
            remove(idx);
        }
        return super.add(e);
    }

    public void show() {
        for (A e : this) {
            e.output();
        }
    }

    public boolean containsProduct(String name) {
        for (A e : this) {
            if (e.getName().contains(name)) {
                return true;
            }
        }
        return false;
    }

    
    protected abstract A parseString(String stringObject);
}
