package GENETIC_KP;

public enum Stuff
{
    APPLE {
        @Override
        public String getName() {
            return "Apple";
        }

        @Override
        public int getUsefulness() {
            return 91;
        }

        @Override
        public int getWeight() {
            return 84;
        }

    },
    ORANGE {
        @Override
        public String getName() {
            return "Orange";
        }

        @Override
        public int getUsefulness() {
            return 72;
        }

        @Override
        public int getWeight() {
            return 83;
        }

    },
    LEMON {
        @Override
        public String getName() {
            return "Lemon";
        }

        @Override
        public int getUsefulness() {
            return 90;
        }

        @Override
        public int getWeight() {
            return 43;
        }

    },
    BLUEBERRY {
        @Override
        public String getName() {
            return "Blueberry";
        }

        @Override
        public int getUsefulness() {
            return 46;
        }

        @Override
        public int getWeight() {
            return 4;
        }

    },
    STRAWBERRY {
        @Override
        public String getName() {
            return "Strawberry";
        }

        @Override
        public int getUsefulness() {
            return 55;
        }

        @Override
        public int getWeight() {
            return 44;
        }

    },
    CARROT {
        @Override
        public String getName() {
            return "Carrot";
        }

        @Override
        public int getUsefulness() {
            return 8;
        }

        @Override
        public int getWeight() {
            return 6;
        }

    },
    PEAR {
        @Override
        public String getName() {
            return "Pear";
        }

        @Override
        public int getUsefulness() {
            return 35;
        }

        @Override
        public int getWeight() {
            return 82;
        }

    },
    KIWI {
        @Override
        public String getName() {
            return "Kiwi";
        }

        @Override
        public int getUsefulness() {
            return 75;
        }

        @Override
        public int getWeight() {
            return 92;
        }

    },

    GLOVES {
        @Override
        public String getName() {
            return "Gloves";
        }

        @Override
        public int getUsefulness() {
            return 61;
        }

        @Override
        public int getWeight() {
            return 25;
        }

    },
    JACKET {
        @Override
        public String getName() {
            return "Jacket";
        }

        @Override
        public int getUsefulness() {
            return 15;
        }

        @Override
        public int getWeight() {
            return 83;
        }

    },
    FANTA {
        @Override
        public String getName() {
            return "Fanta";
        }

        @Override
        public int getUsefulness() {
            return 77;
        }

        @Override
        public int getWeight() {
            return 56;
        }

    },
    COCA {
        @Override
        public String getName() {
            return "Coca";
        }

        @Override
        public int getUsefulness() {
            return 40;
        }

        @Override
        public int getWeight() {
            return 18;
        }

    },


    SHOES {
        @Override
        public String getName() {
            return "Shoes";
        }

        @Override
        public int getUsefulness() {
            return 63;
        }

        @Override
        public int getWeight() {
            return 58;
        }

    },
    HAT {
        @Override
        public String getName() {
            return "Hat";
        }

        @Override
        public int getUsefulness() {
            return 75;
        }

        @Override
        public int getWeight() {
            return 14;
        }

    },
    GLASSES {
        @Override
        public String getName() {
            return "Glasses";
        }

        @Override
        public int getUsefulness() {
            return 29;
        }

        @Override
        public int getWeight() {
            return 48;
        }

    },
    PC {
        @Override
        public String getName() {
            return "Pc";
        }

        @Override
        public int getUsefulness() {
            return 75;
        }

        @Override
        public int getWeight() {
            return 70;
        }

    },
    Phone {
        @Override
        public String getName() {
            return "Phone";
        }

        @Override
        public int getUsefulness() {
            return 17;
        }

        @Override
        public int getWeight() {
            return 96;
        }

    },
    WATER {
        @Override
        public String getName() {
            return "Water";
        }

        @Override
        public int getUsefulness() {
            return 78;
        }

        @Override
        public int getWeight() {
            return 32;
        }

    },


    MCDO {
        @Override
        public String getName() {
            return "McDo";
        }

        @Override
        public int getUsefulness() {
            return 40;
        }

        @Override
        public int getWeight() {
            return 68;
        }

    },
    BEANS {
        @Override
        public String getName() {
            return "Beans";
        }

        @Override
        public int getUsefulness() {
            return 44;
        }

        @Override
        public int getWeight() {
            return 92;
        }

    };

    public abstract int getWeight();
    public abstract String getName();
    public abstract int getUsefulness();

}
