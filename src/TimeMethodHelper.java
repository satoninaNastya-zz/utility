class TimeMethodHelper {

    static long calculateTime(Method entryMethod, Method exitMethod) {
        return exitMethod.getTime() - entryMethod.getTime();
    }
}
