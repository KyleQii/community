package seekgroup.college.community.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Kyle on 0005 2022/5/5.
 * @version 1.0
 */
public class NotificationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NotificationExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNotifierIsNull() {
            addCriterion("NOTIFIER is null");
            return (Criteria) this;
        }

        public Criteria andNotifierIsNotNull() {
            addCriterion("NOTIFIER is not null");
            return (Criteria) this;
        }

        public Criteria andNotifierEqualTo(Long value) {
            addCriterion("NOTIFIER =", value, "notifier");
            return (Criteria) this;
        }

        public Criteria andNotifierNotEqualTo(Long value) {
            addCriterion("NOTIFIER <>", value, "notifier");
            return (Criteria) this;
        }

        public Criteria andNotifierGreaterThan(Long value) {
            addCriterion("NOTIFIER >", value, "notifier");
            return (Criteria) this;
        }

        public Criteria andNotifierGreaterThanOrEqualTo(Long value) {
            addCriterion("NOTIFIER >=", value, "notifier");
            return (Criteria) this;
        }

        public Criteria andNotifierLessThan(Long value) {
            addCriterion("NOTIFIER <", value, "notifier");
            return (Criteria) this;
        }

        public Criteria andNotifierLessThanOrEqualTo(Long value) {
            addCriterion("NOTIFIER <=", value, "notifier");
            return (Criteria) this;
        }

        public Criteria andNotifierIn(List<Long> values) {
            addCriterion("NOTIFIER in", values, "notifier");
            return (Criteria) this;
        }

        public Criteria andNotifierNotIn(List<Long> values) {
            addCriterion("NOTIFIER not in", values, "notifier");
            return (Criteria) this;
        }

        public Criteria andNotifierBetween(Long value1, Long value2) {
            addCriterion("NOTIFIER between", value1, value2, "notifier");
            return (Criteria) this;
        }

        public Criteria andNotifierNotBetween(Long value1, Long value2) {
            addCriterion("NOTIFIER not between", value1, value2, "notifier");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNull() {
            addCriterion("RECEIVER is null");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNotNull() {
            addCriterion("RECEIVER is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverEqualTo(Long value) {
            addCriterion("RECEIVER =", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotEqualTo(Long value) {
            addCriterion("RECEIVER <>", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThan(Long value) {
            addCriterion("RECEIVER >", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThanOrEqualTo(Long value) {
            addCriterion("RECEIVER >=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThan(Long value) {
            addCriterion("RECEIVER <", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThanOrEqualTo(Long value) {
            addCriterion("RECEIVER <=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverIn(List<Long> values) {
            addCriterion("RECEIVER in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotIn(List<Long> values) {
            addCriterion("RECEIVER not in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverBetween(Long value1, Long value2) {
            addCriterion("RECEIVER between", value1, value2, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotBetween(Long value1, Long value2) {
            addCriterion("RECEIVER not between", value1, value2, "receiver");
            return (Criteria) this;
        }

        public Criteria andOuteridIsNull() {
            addCriterion("outerId is null");
            return (Criteria) this;
        }

        public Criteria andOuteridIsNotNull() {
            addCriterion("outerId is not null");
            return (Criteria) this;
        }

        public Criteria andOuteridEqualTo(Long value) {
            addCriterion("outerId =", value, "outerid");
            return (Criteria) this;
        }

        public Criteria andOuteridNotEqualTo(Long value) {
            addCriterion("outerId <>", value, "outerid");
            return (Criteria) this;
        }

        public Criteria andOuteridGreaterThan(Long value) {
            addCriterion("outerId >", value, "outerid");
            return (Criteria) this;
        }

        public Criteria andOuteridGreaterThanOrEqualTo(Long value) {
            addCriterion("outerId >=", value, "outerid");
            return (Criteria) this;
        }

        public Criteria andOuteridLessThan(Long value) {
            addCriterion("outerId <", value, "outerid");
            return (Criteria) this;
        }

        public Criteria andOuteridLessThanOrEqualTo(Long value) {
            addCriterion("outerId <=", value, "outerid");
            return (Criteria) this;
        }

        public Criteria andOuteridIn(List<Long> values) {
            addCriterion("outerId in", values, "outerid");
            return (Criteria) this;
        }

        public Criteria andOuteridNotIn(List<Long> values) {
            addCriterion("outerId not in", values, "outerid");
            return (Criteria) this;
        }

        public Criteria andOuteridBetween(Long value1, Long value2) {
            addCriterion("outerId between", value1, value2, "outerid");
            return (Criteria) this;
        }

        public Criteria andOuteridNotBetween(Long value1, Long value2) {
            addCriterion("outerId not between", value1, value2, "outerid");
            return (Criteria) this;
        }

        public Criteria andNotificationTypeIsNull() {
            addCriterion("NOTIFICATION_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andNotificationTypeIsNotNull() {
            addCriterion("NOTIFICATION_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andNotificationTypeEqualTo(Integer value) {
            addCriterion("NOTIFICATION_TYPE =", value, "notificationType");
            return (Criteria) this;
        }

        public Criteria andNotificationTypeNotEqualTo(Integer value) {
            addCriterion("NOTIFICATION_TYPE <>", value, "notificationType");
            return (Criteria) this;
        }

        public Criteria andNotificationTypeGreaterThan(Integer value) {
            addCriterion("NOTIFICATION_TYPE >", value, "notificationType");
            return (Criteria) this;
        }

        public Criteria andNotificationTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("NOTIFICATION_TYPE >=", value, "notificationType");
            return (Criteria) this;
        }

        public Criteria andNotificationTypeLessThan(Integer value) {
            addCriterion("NOTIFICATION_TYPE <", value, "notificationType");
            return (Criteria) this;
        }

        public Criteria andNotificationTypeLessThanOrEqualTo(Integer value) {
            addCriterion("NOTIFICATION_TYPE <=", value, "notificationType");
            return (Criteria) this;
        }

        public Criteria andNotificationTypeIn(List<Integer> values) {
            addCriterion("NOTIFICATION_TYPE in", values, "notificationType");
            return (Criteria) this;
        }

        public Criteria andNotificationTypeNotIn(List<Integer> values) {
            addCriterion("NOTIFICATION_TYPE not in", values, "notificationType");
            return (Criteria) this;
        }

        public Criteria andNotificationTypeBetween(Integer value1, Integer value2) {
            addCriterion("NOTIFICATION_TYPE between", value1, value2, "notificationType");
            return (Criteria) this;
        }

        public Criteria andNotificationTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("NOTIFICATION_TYPE not between", value1, value2, "notificationType");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("GMT_CREATE is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("GMT_CREATE is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Long value) {
            addCriterion("GMT_CREATE =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Long value) {
            addCriterion("GMT_CREATE <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Long value) {
            addCriterion("GMT_CREATE >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Long value) {
            addCriterion("GMT_CREATE >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Long value) {
            addCriterion("GMT_CREATE <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Long value) {
            addCriterion("GMT_CREATE <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Long> values) {
            addCriterion("GMT_CREATE in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Long> values) {
            addCriterion("GMT_CREATE not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Long value1, Long value2) {
            addCriterion("GMT_CREATE between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Long value1, Long value2) {
            addCriterion("GMT_CREATE not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("\"STATUS\" is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("\"STATUS\" is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("\"STATUS\" =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("\"STATUS\" <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("\"STATUS\" >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("\"STATUS\" >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("\"STATUS\" <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("\"STATUS\" <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("\"STATUS\" in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("\"STATUS\" not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("\"STATUS\" between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("\"STATUS\" not between", value1, value2, "status");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}