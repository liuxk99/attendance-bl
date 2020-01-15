package com.sj.attendance.bl;

import java.util.ArrayList;
import java.util.List;

import static com.sj.attendance.bl.WorkTimePolicyFactory.createFixPolicyAM;
import static com.sj.attendance.bl.WorkTimePolicyFactory.createFixPolicyFD;
import static com.sj.attendance.bl.WorkTimePolicyFactory.createFixPolicyPM;

public class WorkTimePolicySetConfig {
    //{{ WorkTimePolicySet index
    public int getPolicySetIndex() {
        return policySetIndex;
    }

    public void setPolicySetIndex(int policySetIndex) {
        if (policySetIndex != this.policySetIndex) {
            this.policySetIndex = policySetIndex;
            this.policySet = policySetList.get(policySetIndex);
        }
    }

    private int policySetIndex = 0;
    //}}

    //{{ WorkTimePolicySet object
    public WorkTimePolicySet getPolicySet() {
        return policySet;
    }

    public void setPolicySet(WorkTimePolicySet policySet) {
        if (policySet != this.policySet) {
            this.policySet = policySet;

            int index = 0;
            for (WorkTimePolicySet set : policySetList) {
                if (set == policySet) {
                    policySetIndex = index;
                    break;
                }
                index++;
            }
        }
    }

    private WorkTimePolicySet policySet;
    //}}

    //{{ WorkTimePolicySet List
    public List<WorkTimePolicySet> getPolicySetList() {
        return policySetList;
    }

    protected List<WorkTimePolicySet> policySetList = new ArrayList<>();
    //}}

    public WorkTimePolicySetConfig() {
    }

    public void generateDef() {
        FixWorkTimePolicy fixPolicyFD = createFixPolicyFD();
        FixWorkTimePolicy fixPolicyAM = createFixPolicyAM();
        FixWorkTimePolicy fixPolicyPM = createFixPolicyPM();

        FixWorkTimePolicy flexPolicyFD = WorkTimePolicyFactory.createFlexPolicyFD();
        FixWorkTimePolicy flexPolicyAM = WorkTimePolicyFactory.createFlexPolicyAM();

        WorkTimePolicySet flexWorkTimePolicySet = new WorkTimePolicySet("XX集团-弹性工时");
        {
            flexWorkTimePolicySet.addPolicy(flexPolicyFD);
            flexWorkTimePolicySet.addPolicy(flexPolicyAM);
            flexWorkTimePolicySet.addPolicy(fixPolicyPM);
            flexWorkTimePolicySet.setIndex(0);
            flexWorkTimePolicySet.setPolicy(flexPolicyFD);
        }

        WorkTimePolicySet fixWorkTimePolicySet = new WorkTimePolicySet("XX集团-固定工时");
        {
            fixWorkTimePolicySet.addPolicy(fixPolicyFD);
            fixWorkTimePolicySet.addPolicy(fixPolicyAM);
            fixWorkTimePolicySet.addPolicy(fixPolicyPM);

            fixWorkTimePolicySet.setIndex(0);
            fixWorkTimePolicySet.setPolicy(fixPolicyFD);
        }

        policySetList.add(flexWorkTimePolicySet);
        policySetList.add(fixWorkTimePolicySet);

        policySetIndex = 0;
        policySet = flexWorkTimePolicySet;
    }

    public FixWorkTimePolicy getPolicy() {
        return getPolicySet().getPolicy();
    }
}
