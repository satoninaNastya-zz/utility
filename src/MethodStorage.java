import java.util.*;

class MethodStorage {

    private static HashMap<Integer, Method> methodsMap = new HashMap<>();
    private static List<String> invalidMethods = new LinkedList<>();

    private static SummaryUtility summaryUtility = new SummaryUtility();

    static void initMethodStorage(List<String> lineList) {
        lineList.forEach(MethodStorage::putMethod);
        summaryUtility.getTotalSummary();
    }

    static private void putMethod(String line) {
        try {
            Method method = new Method(line);
            if (method.isEntry()) {
                methodsMap.put(method.getID(), method);
            } else {
                addToSummaryAndRemoveFromStorage(method);
            }
        } catch (InvalidFormatLineException e) {
            invalidMethods.add(line);
        }
    }
    static private void addToSummaryAndRemoveFromStorage(Method exitMethod){
        Integer methodID = exitMethod.getID();
        Method entryMethod = methodsMap.get(methodID);
        methodsMap.remove(methodID);
        summaryUtility.addData(entryMethod, exitMethod);
    }


    static private void showMethodWithoutEnd() {
        for (Map.Entry<Integer, Method> entry : methodsMap.entrySet()) {
            System.out.println(entry.getValue().toString());
        }
    }

    static private void showInvalidMethod() {
        for (String invalidMethod : invalidMethods) {
            System.out.println(invalidMethod);
        }
    }
}


