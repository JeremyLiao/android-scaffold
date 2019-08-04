package com.jeremyliao.android.scaffold.base.rule;

import com.jeremyliao.android.scaffold.base.helper.RxSchedulersHelper;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Created by liaohailiang on 2019-08-01.
 */
public class RxSchedulersRule implements TestRule {

    @Override
    public Statement apply(Statement base, Description description) {
        return new RxSchedulersStatement(base);
    }

    class RxSchedulersStatement extends Statement {
        final Statement next;

        public RxSchedulersStatement(Statement next) {
            this.next = next;
        }

        @Override
        public void evaluate() throws Throwable {
            try {
                next.evaluate();
            } finally {
                RxSchedulersHelper.initRxSchedulers();
            }
        }
    }
}
