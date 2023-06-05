
package Dao;
import java.util.List;

import Model.Prestamos;

public interface PrestamosDAO {
    
    List<Prestamos> prestamos();
    Prestamos getPrestamos(int id);
    void save(Prestamos prestamos);
    void update(Prestamos prestamos);
    void delete(Prestamos prestamos);

}





