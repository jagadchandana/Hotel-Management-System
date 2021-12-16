package lk.system.dto;

public class ClassPackageDTO {
    private int id;
    private double luxury;
    private double semiLuxury;
    private double classA;
    private double classB;
    private double classC;
    private double normal;
    private double permenent;

    public ClassPackageDTO(int id, double luxury, double semiLuxury, double classA, double classB, double classC, double normal, double permenent) {
        this.id = id;
        this.luxury = luxury;
        this.semiLuxury = semiLuxury;
        this.classA = classA;
        this.classB = classB;
        this.classC = classC;
        this.normal = normal;
        this.permenent = permenent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLuxury() {
        return luxury;
    }

    public void setLuxury(double luxury) {
        this.luxury = luxury;
    }

    public double getSemiLuxury() {
        return semiLuxury;
    }

    public void setSemiLuxury(double semiLuxury) {
        this.semiLuxury = semiLuxury;
    }

    public double getClassA() {
        return classA;
    }

    public void setClassA(double classA) {
        this.classA = classA;
    }

    public double getClassB() {
        return classB;
    }

    public void setClassB(double classB) {
        this.classB = classB;
    }

    public double getClassC() {
        return classC;
    }

    public void setClassC(double classC) {
        this.classC = classC;
    }

    public double getNormal() {
        return normal;
    }

    public void setNormal(double normal) {
        this.normal = normal;
    }

    public double getPermenent() {
        return permenent;
    }

    public void setPermenent(double permenent) {
        this.permenent = permenent;
    }

    @Override
    public String toString() {
        return "CPackageDTO{" +
                "id=" + id +
                ", luxury=" + luxury +
                ", semiLuxury=" + semiLuxury +
                ", classA=" + classA +
                ", classB=" + classB +
                ", classC=" + classC +
                ", normal=" + normal +
                ", permenent=" + permenent +
                '}';
    }
}
