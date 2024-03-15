package com.springboot.valid_exception.common;

public class Constants {

    public enum ExceptionClass {


        PRODUCT("product");

        private String excptionClass;

        ExceptionClass(String excptionClass){
            this.excptionClass = excptionClass;
        }

        public String getExcptionClass(){
            return excptionClass;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
