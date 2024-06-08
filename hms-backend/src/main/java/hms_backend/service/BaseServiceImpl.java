package hms_backend.service;

import hms_backend.base.BaseComponent;
import hms_backend.model.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
public abstract class BaseServiceImpl<T extends BaseModel> extends BaseComponent implements BaseService<T,Long>{

   protected abstract JpaRepository<T,Long> getRepository();

    @Override
    public T create(final T item) {
        return getRepository().save(item);
    }

    @Override
    @Transactional
    public void update(final T item) {
        getRepository().save(item);
    }

    @Override
    @Transactional(readOnly = true)
    public T getById(final Long id) {
        return getRepository().findById(id).orElseThrow();
    }

    @Override
    public void deleteById(final Long id) {
        getRepository().deleteById(id);
    }

    @Override
    public void delete(final T item) {
        getRepository().delete(item);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Long count(){
        return getRepository().count();
    }
}
