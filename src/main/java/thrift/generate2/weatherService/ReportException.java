/**
 * Autogenerated by Thrift Compiler (0.12.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package thrift.generate2.weatherService;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
/**
 * This Exception gets thrown when a wrong report is send to the system.
 */
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.12.0)", date = "2019-05-25")
public class ReportException extends org.apache.thrift.TException implements org.apache.thrift.TBase<ReportException, ReportException._Fields>, java.io.Serializable, Cloneable, Comparable<ReportException> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ReportException");

  private static final org.apache.thrift.protocol.TField REPORT_FIELD_DESC = new org.apache.thrift.protocol.TField("report", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField WHY_FIELD_DESC = new org.apache.thrift.protocol.TField("why", org.apache.thrift.protocol.TType.STRING, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new ReportExceptionStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new ReportExceptionTupleSchemeFactory();

  /**
   * 
   * @see Report
   */
  public @org.apache.thrift.annotation.Nullable Report report; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String why; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * 
     * @see Report
     */
    REPORT((short)1, "report"),
    WHY((short)2, "why");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // REPORT
          return REPORT;
        case 2: // WHY
          return WHY;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.REPORT, new org.apache.thrift.meta_data.FieldMetaData("report", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, Report.class)));
    tmpMap.put(_Fields.WHY, new org.apache.thrift.meta_data.FieldMetaData("why", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ReportException.class, metaDataMap);
  }

  public ReportException() {
  }

  public ReportException(
    Report report,
    java.lang.String why)
  {
    this();
    this.report = report;
    this.why = why;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ReportException(ReportException other) {
    if (other.isSetReport()) {
      this.report = other.report;
    }
    if (other.isSetWhy()) {
      this.why = other.why;
    }
  }

  public ReportException deepCopy() {
    return new ReportException(this);
  }

  @Override
  public void clear() {
    this.report = null;
    this.why = null;
  }

  /**
   * 
   * @see Report
   */
  @org.apache.thrift.annotation.Nullable
  public Report getReport() {
    return this.report;
  }

  /**
   * 
   * @see Report
   */
  public ReportException setReport(@org.apache.thrift.annotation.Nullable Report report) {
    this.report = report;
    return this;
  }

  public void unsetReport() {
    this.report = null;
  }

  /** Returns true if field report is set (has been assigned a value) and false otherwise */
  public boolean isSetReport() {
    return this.report != null;
  }

  public void setReportIsSet(boolean value) {
    if (!value) {
      this.report = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getWhy() {
    return this.why;
  }

  public ReportException setWhy(@org.apache.thrift.annotation.Nullable java.lang.String why) {
    this.why = why;
    return this;
  }

  public void unsetWhy() {
    this.why = null;
  }

  /** Returns true if field why is set (has been assigned a value) and false otherwise */
  public boolean isSetWhy() {
    return this.why != null;
  }

  public void setWhyIsSet(boolean value) {
    if (!value) {
      this.why = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case REPORT:
      if (value == null) {
        unsetReport();
      } else {
        setReport((Report)value);
      }
      break;

    case WHY:
      if (value == null) {
        unsetWhy();
      } else {
        setWhy((java.lang.String)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case REPORT:
      return getReport();

    case WHY:
      return getWhy();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case REPORT:
      return isSetReport();
    case WHY:
      return isSetWhy();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof ReportException)
      return this.equals((ReportException)that);
    return false;
  }

  public boolean equals(ReportException that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_report = true && this.isSetReport();
    boolean that_present_report = true && that.isSetReport();
    if (this_present_report || that_present_report) {
      if (!(this_present_report && that_present_report))
        return false;
      if (!this.report.equals(that.report))
        return false;
    }

    boolean this_present_why = true && this.isSetWhy();
    boolean that_present_why = true && that.isSetWhy();
    if (this_present_why || that_present_why) {
      if (!(this_present_why && that_present_why))
        return false;
      if (!this.why.equals(that.why))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetReport()) ? 131071 : 524287);
    if (isSetReport())
      hashCode = hashCode * 8191 + report.getValue();

    hashCode = hashCode * 8191 + ((isSetWhy()) ? 131071 : 524287);
    if (isSetWhy())
      hashCode = hashCode * 8191 + why.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(ReportException other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetReport()).compareTo(other.isSetReport());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetReport()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.report, other.report);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetWhy()).compareTo(other.isSetWhy());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetWhy()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.why, other.why);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("ReportException(");
    boolean first = true;

    sb.append("report:");
    if (this.report == null) {
      sb.append("null");
    } else {
      sb.append(this.report);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("why:");
    if (this.why == null) {
      sb.append("null");
    } else {
      sb.append(this.why);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ReportExceptionStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ReportExceptionStandardScheme getScheme() {
      return new ReportExceptionStandardScheme();
    }
  }

  private static class ReportExceptionStandardScheme extends org.apache.thrift.scheme.StandardScheme<ReportException> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ReportException struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // REPORT
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.report = thrift.generate2.weatherService.Report.findByValue(iprot.readI32());
              struct.setReportIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // WHY
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.why = iprot.readString();
              struct.setWhyIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, ReportException struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.report != null) {
        oprot.writeFieldBegin(REPORT_FIELD_DESC);
        oprot.writeI32(struct.report.getValue());
        oprot.writeFieldEnd();
      }
      if (struct.why != null) {
        oprot.writeFieldBegin(WHY_FIELD_DESC);
        oprot.writeString(struct.why);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ReportExceptionTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ReportExceptionTupleScheme getScheme() {
      return new ReportExceptionTupleScheme();
    }
  }

  private static class ReportExceptionTupleScheme extends org.apache.thrift.scheme.TupleScheme<ReportException> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ReportException struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetReport()) {
        optionals.set(0);
      }
      if (struct.isSetWhy()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetReport()) {
        oprot.writeI32(struct.report.getValue());
      }
      if (struct.isSetWhy()) {
        oprot.writeString(struct.why);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ReportException struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.report = thrift.generate2.weatherService.Report.findByValue(iprot.readI32());
        struct.setReportIsSet(true);
      }
      if (incoming.get(1)) {
        struct.why = iprot.readString();
        struct.setWhyIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}
