public class Planet {
  final double G = 6.67e-11;
  double xxPos;
  double yyPos;
  double xxVel;
  double yyVel;
  double mass;
  String imgFileName;
  
  public Planet(double xP, double yP, double xV, double yV, double m, String img ) {
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }
  public Planet(Planet p) {
    xxPos = p.xxPos;
    yyPos = p.yyPos;
    xxVel = p.xxVel;
    yyVel = p.yyVel;
    mass = p.mass;
    imgFileName = p.imgFileName;
  }
  public double calcDistance(Planet p) {
    return Math.sqrt(Math.abs(Math.pow(p.xxPos - xxPos, 2)) +
          Math.abs(Math.pow(p.yyPos - yyPos, 2)));
  }
  
  public double calcForceExertedBy(Planet p) {
    double distance = calcDistance(p);
    double F = G * mass * p.mass / Math.pow(distance, 2);
    return F;
  }
  public double calcForceExertedByX(Planet p) {
    double Fx = calcForceExertedBy(p) * (p.xxPos - xxPos) / calcDistance(p);
    return Fx;
  }
  public double calcForceExertedByY(Planet p) {
    double Fy = calcForceExertedBy(p) * (p.yyPos - yyPos) / calcDistance(p);
    return Fy;

  }

  public double calcNetForceExertedByX(Planet[] p) {
    double rel = 0;
    for (Planet p1: p) {
      if (this == p1) {
        continue;
      }
      else {
        rel += calcForceExertedByX(p1);
      }
    }
    return rel;
  }
  public double calcNetForceExertedByY(Planet[] p) {
    double rel = 0;
    for (Planet p1: p) {
      if (this == p1) {
        continue;
      }
      else {
        rel += calcForceExertedByY(p1);
      }
    }
    return rel;
      
  }
}
