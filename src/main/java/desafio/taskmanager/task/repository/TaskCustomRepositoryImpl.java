package desafio.taskmanager.task.repository;

import desafio.taskmanager.task.entity.Task;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

public class TaskCustomRepositoryImpl implements TaskCustomRepository {

    @PersistenceContext
    private EntityManager entityManager; //TODO FINAL

    @Override
    public List<Task> listTasksByFilters(LocalDate initialDate, LocalDate finalDate, boolean strict) {
        StringBuilder sb = new StringBuilder();
        sb.append("select s ");
        sb.append("from Task s ");
        sb.append("where s.initialDate >= :initialDate ");

        if (finalDate != null) {
            sb.append("and s.initialDate <= :finalDate ");

            if(strict){
                sb.append("and s.finalDate <= :finalDate");
            }
        }

        Query query = entityManager.createQuery(sb.toString());
        query.setParameter("initialDate", initialDate);

        if (finalDate != null) {
            query.setParameter("finalDate", finalDate);
        }

        return query.getResultList();
    }


//    public List<Task> listTasksByFilters(LocalDate initialDate/*, LocalDate finalDate, boolean absoluteDate*/) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("select s");
//        sb.append("from Task s");
////        sb.append("where s.initialDate >= :initialDate s");
//
//        Query query = entityManager.createQuery(sb.toString());
//        return query.getResultList();
//    }
}
