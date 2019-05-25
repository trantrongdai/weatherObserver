/**
 * Autogenerated by Thrift Compiler (0.12.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.thrift.generate2.weatherService;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
/**
 * This Exception gets thrown when the server does not know about the user.
 */
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.12.0)", date = "2019-05-25")
public class UnknownUserException extends org.apache.thrift.TException implements org.apache.thrift.TBase<UnknownUserException, UnknownUserException._Fields>, java.io.Serializable, Cloneable, Comparable<UnknownUserException> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("UnknownUserException");

  private static final org.apache.thrift.protocol.TField SESSION_TOKEN_FIELD_DESC = new org.apache.thrift.protocol.TField("SessionToken", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField WHY_FIELD_DESC = new org.apache.thrift.protocol.TField("why", org.apache.thrift.protocol.TType.STRING, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new UnknownUserExceptionStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new UnknownUserExceptionTupleSchemeFactory();

  public long SessionToken; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String why; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    SESSION_TOKEN((short)1, "SessionToken"),
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
        case 1: // SESSION_TOKEN
          return SESSION_TOKEN;
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
  private static final int __SESSIONTOKEN_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.SESSION_TOKEN, new org.apache.thrift.meta_data.FieldMetaData("SessionToken", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.WHY, new org.apache.thrift.meta_data.FieldMetaData("why", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(UnknownUserException.class, metaDataMap);
  }

  public UnknownUserException() {
  }

  public UnknownUserException(
    long SessionToken,
    java.lang.String why)
  {
    this();
    this.SessionToken = SessionToken;
    setSessionTokenIsSet(true);
    this.why = why;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public UnknownUserException(UnknownUserException other) {
    __isset_bitfield = other.__isset_bitfield;
    this.SessionToken = other.SessionToken;
    if (other.isSetWhy()) {
      this.why = other.why;
    }
  }

  public UnknownUserException deepCopy() {
    return new UnknownUserException(this);
  }

  @Override
  public void clear() {
    setSessionTokenIsSet(false);
    this.SessionToken = 0;
    this.why = null;
  }

  public long getSessionToken() {
    return this.SessionToken;
  }

  public UnknownUserException setSessionToken(long SessionToken) {
    this.SessionToken = SessionToken;
    setSessionTokenIsSet(true);
    return this;
  }

  public void unsetSessionToken() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __SESSIONTOKEN_ISSET_ID);
  }

  /** Returns true if field SessionToken is set (has been assigned a value) and false otherwise */
  public boolean isSetSessionToken() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __SESSIONTOKEN_ISSET_ID);
  }

  public void setSessionTokenIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __SESSIONTOKEN_ISSET_ID, value);
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getWhy() {
    return this.why;
  }

  public UnknownUserException setWhy(@org.apache.thrift.annotation.Nullable java.lang.String why) {
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
    case SESSION_TOKEN:
      if (value == null) {
        unsetSessionToken();
      } else {
        setSessionToken((java.lang.Long)value);
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
    case SESSION_TOKEN:
      return getSessionToken();

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
    case SESSION_TOKEN:
      return isSetSessionToken();
    case WHY:
      return isSetWhy();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof UnknownUserException)
      return this.equals((UnknownUserException)that);
    return false;
  }

  public boolean equals(UnknownUserException that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_SessionToken = true;
    boolean that_present_SessionToken = true;
    if (this_present_SessionToken || that_present_SessionToken) {
      if (!(this_present_SessionToken && that_present_SessionToken))
        return false;
      if (this.SessionToken != that.SessionToken)
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

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(SessionToken);

    hashCode = hashCode * 8191 + ((isSetWhy()) ? 131071 : 524287);
    if (isSetWhy())
      hashCode = hashCode * 8191 + why.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(UnknownUserException other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetSessionToken()).compareTo(other.isSetSessionToken());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSessionToken()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.SessionToken, other.SessionToken);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("UnknownUserException(");
    boolean first = true;

    sb.append("SessionToken:");
    sb.append(this.SessionToken);
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class UnknownUserExceptionStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public UnknownUserExceptionStandardScheme getScheme() {
      return new UnknownUserExceptionStandardScheme();
    }
  }

  private static class UnknownUserExceptionStandardScheme extends org.apache.thrift.scheme.StandardScheme<UnknownUserException> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, UnknownUserException struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // SESSION_TOKEN
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.SessionToken = iprot.readI64();
              struct.setSessionTokenIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, UnknownUserException struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(SESSION_TOKEN_FIELD_DESC);
      oprot.writeI64(struct.SessionToken);
      oprot.writeFieldEnd();
      if (struct.why != null) {
        oprot.writeFieldBegin(WHY_FIELD_DESC);
        oprot.writeString(struct.why);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class UnknownUserExceptionTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public UnknownUserExceptionTupleScheme getScheme() {
      return new UnknownUserExceptionTupleScheme();
    }
  }

  private static class UnknownUserExceptionTupleScheme extends org.apache.thrift.scheme.TupleScheme<UnknownUserException> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, UnknownUserException struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetSessionToken()) {
        optionals.set(0);
      }
      if (struct.isSetWhy()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetSessionToken()) {
        oprot.writeI64(struct.SessionToken);
      }
      if (struct.isSetWhy()) {
        oprot.writeString(struct.why);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, UnknownUserException struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.SessionToken = iprot.readI64();
        struct.setSessionTokenIsSet(true);
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

