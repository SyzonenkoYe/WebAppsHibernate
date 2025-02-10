package cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.enums;


public enum OfficeWorkerStatus {
    intern("Intern"),
    junior("Junior"),
    middle("Middle"),
    senior("Senior"),
    lead("Lead");

        private final String displayName;

        OfficeWorkerStatus(String displayName) {
            this.displayName = displayName;
        }

        public static OfficeWorkerStatus getOfficeWorkerStatusById(Integer index) {
            if (index >= OfficeWorkerStatus.values().length) {
                return OfficeWorkerStatus.values()[0];
            } else {
                return OfficeWorkerStatus.values()[index];
            }
        }

        public String getDisplayName() {
            return displayName;
        }

        public static String[] getOffWorkerStatus() {
            OfficeWorkerStatus[] pl = values();
            String[] plNames = new String[pl.length];
            for (int i = 0; i < pl.length; i++) {
                plNames[i] = pl[i].getDisplayName();
            }
            return plNames;
        }

        public static int getEnumIndex(String value) {
            int index = -1;
            OfficeWorkerStatus[] pl = values();
            for (OfficeWorkerStatus lang: pl) {
                index++;
                if (lang.getDisplayName().equals(value)) {
                    break;
                }
            }
            return index;
        }



    public static OfficeWorkerStatus getOfficeWorkerStatusByName(String namePL) {
        int index = -1;
        OfficeWorkerStatus[] plValues = values();
        boolean flFound = false;
        while (!flFound && index<plValues.length-1) {
            index++;
            if (plValues[index].getDisplayName().equals(namePL)) {
                flFound = true;
            }
        }
        OfficeWorkerStatus pl = null;
        if (!flFound) {
            pl = OfficeWorkerStatus.values()[0];
        } else {
            pl = OfficeWorkerStatus.values()[index];
        }
        return pl;
    }




    public static String[]  getOfficeWorkerStatus() {
            OfficeWorkerStatus[] pl = values();
            String[] plNames = new String[pl.length];
            for (int i = 0; i < pl.length; i++) {
                plNames[i] = pl[i].getDisplayName();
            }
            return plNames;
        }

    }
