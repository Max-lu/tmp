package cn.maxlu.demo.spring.mybatis;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import sun.dc.pr.PRError;

import java.sql.SQLException;

@Service
public class DemoService {

    @Autowired
    private IDemoDao demoDao;

//    @Autowired
    private TransactionTemplate transactionTemplate;

    @Transactional
    public void insertDemo() {
        s();
    }

    private void s() {
//        TransactionStatus transactionStatus = TransactionAspectSupport.currentTransactionStatus();
//        System.out.println(transactionStatus);

        DemoInfo d1 = demoDao.selectByName("max1");
        System.out.println("before:" + d1);

        DemoInfo demoInfo1 = new DemoInfo();
        demoInfo1.setName("max1");
        demoDao.insertDemo(demoInfo1);
        System.out.println(demoInfo1.getId());

        DemoInfo d2 = demoDao.selectByName("max1");
        System.out.println("before:" + d2);

        DemoInfo demoInfo2 = new DemoInfo();
        demoInfo2.setName("max2");
        demoDao.insertDemo(demoInfo2);
        System.out.println(demoInfo2.getId());

//        throw new RuntimeException("");
    }


    public int insertDemo3() {
        return transactionTemplate.execute(new TransactionCallback<Integer>() {
            @Override
            public Integer doInTransaction(TransactionStatus status) {
                DemoInfo demoInfo1 = new DemoInfo();
                demoInfo1.setName("max1");
                try {
                    demoDao.insertDemo(demoInfo1);
                    throw new RuntimeException("");
                } catch (Exception e) {
                    status.setRollbackOnly();
                }
                return demoInfo1.getId();
            }
        });
    }

    @Autowired
    private PlatformTransactionManager txManager;

    public void insertDemo4() {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(def);
        try {
            DemoInfo demoInfo1 = new DemoInfo();
            demoInfo1.setName("max1");
            demoDao.insertDemo(demoInfo1);
            txManager.commit(status);
        } catch (RuntimeException e) {
            txManager.rollback(status);
        }
    }
}
