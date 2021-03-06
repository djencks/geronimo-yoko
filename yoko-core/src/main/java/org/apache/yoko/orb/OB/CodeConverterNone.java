/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
*  contributor license agreements.  See the NOTICE file distributed with
*  this work for additional information regarding copyright ownership.
*  The ASF licenses this file to You under the Apache License, Version 2.0
*  (the "License"); you may not use this file except in compliance with
*  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.yoko.orb.OB;

final class CodeConverterNone extends CodeConverterBase {
    // ------------------------------------------------------------------
    // Constructor
    // ------------------------------------------------------------------

    CodeConverterNone(CodeSetInfo fromSet, CodeSetInfo toSet) {
        super(fromSet, toSet);
    }

    // ------------------------------------------------------------------
    // Public member implementations
    // ------------------------------------------------------------------

    public boolean conversionRequired() {
        return true;
    }

    public char read_wchar(org.apache.yoko.orb.CORBA.InputStream in, int len) {
        throw new org.omg.CORBA.BAD_PARAM(org.apache.yoko.orb.OB.MinorCodes
                .describeBadParam(org.apache.yoko.orb.OB.MinorCodes.MinorNoWcharCodeSet),
                org.apache.yoko.orb.OB.MinorCodes.MinorNoWcharCodeSet,
                org.omg.CORBA.CompletionStatus.COMPLETED_NO);
    }

    public void write_wchar(org.apache.yoko.orb.CORBA.OutputStream out, char v) {
        throw new org.omg.CORBA.INV_OBJREF(org.apache.yoko.orb.OB.MinorCodes
                .describeInvObjref(org.apache.yoko.orb.OB.MinorCodes.MinorWcharCodeSetRequired),
                org.apache.yoko.orb.OB.MinorCodes.MinorWcharCodeSetRequired,
                org.omg.CORBA.CompletionStatus.COMPLETED_NO);
    }

    public int read_count_wchar(char value) {
        throw new org.omg.CORBA.BAD_PARAM(org.apache.yoko.orb.OB.MinorCodes
                .describeBadParam(org.apache.yoko.orb.OB.MinorCodes.MinorNoWcharCodeSet),
                org.apache.yoko.orb.OB.MinorCodes.MinorNoWcharCodeSet,
                org.omg.CORBA.CompletionStatus.COMPLETED_NO);
    }

    public int write_count_wchar(char v) {
        throw new org.omg.CORBA.INV_OBJREF(org.apache.yoko.orb.OB.MinorCodes
                .describeInvObjref(org.apache.yoko.orb.OB.MinorCodes.MinorWcharCodeSetRequired),
                org.apache.yoko.orb.OB.MinorCodes.MinorWcharCodeSetRequired,
                org.omg.CORBA.CompletionStatus.COMPLETED_NO);
    }

    public char convert(char v) {
        throw new org.omg.CORBA.INV_OBJREF(org.apache.yoko.orb.OB.MinorCodes
                .describeInvObjref(org.apache.yoko.orb.OB.MinorCodes.MinorWcharCodeSetRequired),
                org.apache.yoko.orb.OB.MinorCodes.MinorWcharCodeSetRequired,
                org.omg.CORBA.CompletionStatus.COMPLETED_NO);
    }
}
