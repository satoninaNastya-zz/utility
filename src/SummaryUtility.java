import com.sun.istack.internal.Nullable;

import java.util.HashMap;
import java.util.Map;

class SummaryUtility {

    private static HashMap<String, Summary> totalSummary = new HashMap<>();

    void addData(Method entryMethod, Method exitMethod) {
        String methodName = entryMethod.getName();
        @Nullable Summary methodSummary = totalSummary.get(methodName);

        if (methodSummary == null) {
            Summary newSummary = new Summary();
            newSummary.addData(entryMethod, exitMethod);
            totalSummary.put(methodName, newSummary);
        } else {
            methodSummary.addData(entryMethod, exitMethod);
        }
    }

    void getTotalSummary(){
        for (Map.Entry<String, Summary> entry : totalSummary.entrySet()) {
            System.out.println(entry.getValue().toString());
        }
    }

    private class Summary {

        private String summaryMethodName = null;
        private float min = 100000000;
        private float sum = 0;
        private float max = 0;
        private int count = 0;
        private int maxID;

        void addData(Method entryMethod, Method exitMethod) {
            if(summaryMethodName == null){
                summaryMethodName = entryMethod.getName();
            }

            long time = TimeMethodHelper.calculateTime(entryMethod,exitMethod);

            count ++;
            checkMax(time, entryMethod.getID());
            checkMin(time);
            sum = sum + time;
        }

        private void checkMin(long time){
            if (time < min){
                min = time;
            }
        }

        private void checkMax(long time, int methodID){
            if (time > max){
                max = time;
                maxID = methodID;
            }
        }

        float getAvg() {
            return sum / count;
        }

        @Override
        public String toString() {
            return "OperationsImpl:" + summaryMethodName + " min " + min + ", max " + max + ", avr " + getAvg() + ", max id " + maxID + ", count " + count;
        }
    }
}
